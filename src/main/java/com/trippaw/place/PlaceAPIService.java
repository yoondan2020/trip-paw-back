package com.trippaw.place;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
public class PlaceAPIService {

    @Value("${api.key}")
    private String APIKey;

    private PlaceService placeService;

    public PlaceAPIService(PlaceService placeService){
        this.placeService = placeService;
    }

    private int areaCode = 1;

    private static final Map<String, String> contentTypeIdMap = Map.of(
            "12", "관광지",
            "14", "문화시설",
            "15", "축제/공연/행사",
            "25", "여행코스",
            "28", "레포츠",
            "32", "숙박",
            "38", "쇼핑",
            "39", "음식점"
    );

    public void FetchPlace() {

        try{
            // 1. URL 객체 생성
            URL url = new URL(
                    "https://apis.data.go.kr/B551011/KorPetTourService2/areaBasedList2?serviceKey="
                        + APIKey
                        + "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=C"
                        + "&areaCode="
                        + areaCode
                        + "&_type=json"

            );

            // 2. 연결 생성 및 요청 방식 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 3. 응답코드 확인
            int responseCode = connection.getResponseCode();

            // 4. 응답코드가 200일 때(성공)만 데이터 처리
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                while ((inputLine = in.readLine()) != null ){
                    content.append(inputLine);
                }
                in.close();
                System.out.println("Response: " + content.toString());

                // java 객체로 옮기기
                JSONObject jsonObject = (JSONObject) JSONValue.parse(content.toString());

                JSONObject response = (JSONObject) jsonObject.get("response");
                JSONObject body = (JSONObject) response.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray infoItems = (JSONArray) items.get("item");

                System.out.println(infoItems);

                // API 응답의 각 장소 정보를 Place 객체로 변환
                for (Object obj : infoItems) {
                    JSONObject item = (JSONObject) obj;

                    Place place = new Place();

                    place.setContentId((String) item.get("contentid"));
                    place.setContentTypeId(Integer.parseInt((String) item.get("contenttypeid")));
                    place.setName((String) item.get("title"));
                    place.setAddress((String) item.get("addr1"));
                    place.setDetailAddress((String) item.get("addr2"));
                    place.setCreatedTime((String) item.get("created time"));
                    place.setModifiedTime((String) item.get("modifiedtime"));
                    place.setTel((String) item.get("tel"));
                    place.setThumbnailUrl((String) item.get("firstimage"));

                    place.setAreacode(Integer.parseInt((String) item.get("areacode")));
                    place.setSigungucode(Integer.parseInt((String) item.get("sigungucode")));

                    place.setMapx(Double.parseDouble((String) item.get("mapx")));
                    place.setMapy(Double.parseDouble((String) item.get("mapy")));

                    System.out.println(place);

                    placeService.save(place);
                }

            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

            // 5. 연결 종료
            connection.disconnect();

        } catch ( Exception e) {
           e.printStackTrace();
        }

    }

}
