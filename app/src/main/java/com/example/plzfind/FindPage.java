package com.example.plzfind;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FindPage extends AppCompatActivity {

    private ListView listView;
    private EditText editTextSearch;
    private List<PostItem> originalDataList;
    private CustomAdapter adapter;
    Button btnGoToFindWritePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpage);

        listView = findViewById(R.id.listView);
        editTextSearch = findViewById(R.id.editTextSearch);

        ImageButton btnSearch = findViewById(R.id.btnSearch);
        ImageButton btnmenufind = findViewById(R.id.btn_menu_find);
        btnGoToFindWritePost = findViewById(R.id.btnGoToFindPage);

        btnGoToFindWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭했을 때 LookWritePost 액티비티로 이동
                Intent intent = new Intent(FindPage.this, FindWritePost.class);
                startActivity(intent);
            }
        });


        btnmenufind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사이드바 액티비티 열기
                Intent intent = new Intent(FindPage.this, Sidebar.class);
                startActivity(intent);
            }
        });

        // 기본 데이터 로드
        originalDataList = new ArrayList<>();
        originalDataList.add(new PostItem("에어팟 찾았습니다", "에어팟 본부동에서 찾았습니다...", "2023-12-01 23:17:44", "본부동", PostItem.getAirpotImage()));
        originalDataList.add(new PostItem("휴대폰 찾았습니다", "교수회관에서 휴대폰을 주웠는데 식당에서 밥 먹다가 놓고 가신거 같아요 교수회관 아주머니께 맡겨놓고 갑니다", "2023-12-02 10:30:00", "교수회관", PostItem.getPhoneImage()));
        originalDataList.add(new PostItem("지갑을 발견했습니다!!", "식당에서 지갑을 주웠습니다 기숙사 식당 밥뜨랑에서 주웠구요 시간이 없어서 그냥 그자리에 놓고 나왔어요", "2023-12-03 15:45:22", "기숙사", PostItem.getMoneyImage()));
        originalDataList.add(new PostItem("카드 발견", "복지동을 지나다가 카드를 발견했습니다 연락주세요 길 지나가다가 기숙사 카드가 떨어져있어서 제가 가지고 있습니다 연락주세요", "2023-12-05 17:05:21", "복지동", PostItem.getCardImage()));
        originalDataList.add(new PostItem("노트북 충전기", "지혜관 4층 402호에서 찾았습니다 전 수업 시간에 사용하신거 같은데 목요일 3교시 였던거 같습니다", "2023-12-05 21:48:51", "지혜관", PostItem.getChangerImage()));
        originalDataList.add(new PostItem("키링 주웠어요", "사물함 위에 놓고 가겠습니다 찾아가세요! 키링이 많이 더러워졌던데 꼭 찾아가셨으면 좋겠습니당ㅜㅜ", "2023-12-05 17:05:21", "본부동", PostItem.getBearImage()));

        // 어댑터 초기화
        adapter = new CustomAdapter(FindPage.this, originalDataList);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 리스트뷰 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 아이템의 정보 가져오기
                PostItem selectedItem = originalDataList.get(position);

                // 상세 페이지로 전환하는 Intent 생성
                Intent intent = new Intent(FindPage.this, DetailActivity.class);
                // 정보 전달
                intent.putExtra("selectedItem", selectedItem);
                // 상세 페이지로 이동
                startActivity(intent);
            }
        });

        // 검색 버튼 클릭 이벤트 처리
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색어 가져오기
                String searchTerm = editTextSearch.getText().toString().trim();

                // AsyncTask 실행
                new FetchDataTask().execute(searchTerm);
            }
        });
    }


    private class FetchDataTask extends AsyncTask<String, Void, List<PostItem>> {
        @Override
        protected List<PostItem> doInBackground(String... searchTerms) {
            List<PostItem> filteredDataList = new ArrayList<>();

            if (searchTerms == null || searchTerms.length == 0 || searchTerms[0].isEmpty()) {
                return originalDataList;
            }

            String searchTermLowerCase = searchTerms[0].toLowerCase();

            for (PostItem item : originalDataList) {
                if (item.getTitle().toLowerCase().contains(searchTermLowerCase)
                        || item.getContent().toLowerCase().contains(searchTermLowerCase)
                        || item.getPlace().toLowerCase().contains(searchTermLowerCase)
                        || item.getRegDate().toLowerCase().contains(searchTermLowerCase)) {
                    filteredDataList.add(item);
                }
            }

            return filteredDataList;
        }

        @Override
        protected void onPostExecute(List<PostItem> filteredDataList) {
            try {
                // 어댑터에 필터링된 데이터를 설정
                adapter.clear(); // 기존 데이터를 모두 제거
                adapter.addAll(filteredDataList); // 필터링된 데이터를 추가
                adapter.notifyDataSetChanged(); // 어댑터 갱신
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
    private class FetchDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            try {
                // PHP 서버 URL
                String phpUrl = "http://bufind.dothome.co.kr/findpage_list.php";
                // PHP 서버에 연결
                URL url = new URL(phpUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 연결 설정
                connection.setRequestMethod("GET");
                // 응답 코드 확인
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 스트림을 통해 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();
                } else {
                    result.append("데이터 가져오기 실패. 응답 코드: ").append(responseCode);
                }

                // 연결 종료
                connection.disconnect();

            } catch (Exception e) {
                result.append("예외 발생: ").append(e.getMessage());
            }
            return result.toString();
        }



        @Override
        protected void onPostExecute(String result) {
            try {
                // 네트워크 요청 부분 주석 처리
                // JSONObject jsonData = new JSONObject(result);
                // JSONArray jsonArray = jsonData.getJSONArray("찾아요게시판");

                // 데이터를 저장할 ArrayList 생성
                List<String> dataList = new ArrayList<>();

                // 네트워크 요청 부분 대신 임의의 데이터 추가
                dataList.add("제목: 에어팟 찾았습니다\n내용: 에어팟 본부동에서 찾았습니다\n작성시간: 2023-12-01 23:17:44");
                dataList.add("제목: 휴대폰 찾았습니다\n내용: 학생회관에서 휴대폰을 찾았습니다\n작성시간: 2023-12-02 10:30:00");
                dataList.add("제목: 지갑을 발견했습니다!!\n내용: 식당에서 지갑을 주웠습니다\n작성시간: 2023-12-03 15:45:22");
                dataList.add("제목: 카드 발견\n내용: 복지동을 지나다가 카드를 발견했습니당 연락주세요\n작성시간: 2023-12-05 17:05:21");
                dataList.add("제목: 노트북 충전기\n내용: 지혜관 4층 402호에서 찾았습니다\n작성시간: 2023-12-05 21:48:51");
                dataList.add("제목: 키링 주웠어요\n내용: 사물함 위에 놓고 가겠습니다 찾아가세요!\n작성시간: 2023-12-05 17:05:21");

                // 어댑터 초기화
                CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataList);

                // 리스트뷰에 어댑터 설정
                listView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */

/*
        @Override
        protected void onPostExecute(String result) {
            try {
                Log.d("MainActivity", "onPostExecute: Result - " + result);

                JSONObject jsonData = new JSONObject(result);
                JSONArray jsonArray = jsonData.getJSONArray("찾아요게시판");

                // 데이터를 저장할 ArrayList 생성
                List<String> dataList = new ArrayList<>();

                // JSONArray에서 데이터를 추출하여 ArrayList에 추가
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String bContent = jsonObject.getString("b_content");
                    String bRegdate = jsonObject.getString("b_regdate");

                    // 데이터를 원하는 형태로 가공하여 dataList에 추가
                    String formattedData = "title: " + title + "\nb_content: " + bContent + "\nb_regdate: " + bRegdate;
                    dataList.add(formattedData);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 어댑터 초기화
                        CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataList);

                        // 리스트뷰에 어댑터 설정
                        listView.setAdapter(adapter);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
*/

