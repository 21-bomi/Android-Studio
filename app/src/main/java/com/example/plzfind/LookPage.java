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

public class LookPage extends AppCompatActivity {

    private ListView listView;
    private EditText editTextSearch;
    private List<PostItem> originalDataList;
    private CustomAdapter adapter;
    Button btnGoToLookWritePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookpage);

        listView = findViewById(R.id.listViewLook);
        editTextSearch = findViewById(R.id.editTextLook);

        ImageButton btnSearch = findViewById(R.id.btnSearchLook);
        ImageButton btnMenuLook = findViewById(R.id.btn_menu_look);

        btnGoToLookWritePost = findViewById(R.id.btnGoToLookPage);
        btnGoToLookWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭했을 때 LookWritePost 액티비티로 이동
                Intent intent = new Intent(LookPage.this, LookWritePost.class);
                startActivity(intent);
            }
        });


        btnMenuLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사이드바 액티비티 열기
                Intent intent = new Intent(LookPage.this, Sidebar.class);
                startActivity(intent);
            }
        });

        // 기본 데이터 로드
        originalDataList = new ArrayList<>();
        originalDataList.add(new PostItem("우산 찾습니다ㅜㅜ", "어제 복지동에서 수업 듣고 우산 잘못가져간 사람 저한테 연락주세요 비슷한 사진 첨부합니다 파란색 새가 그려져 있는 우산 찾습니다ㅜㅜ", "2023-12-10 14:30:00", "복지동", PostItem.getBirdImage()));
        originalDataList.add(new PostItem("도서관에서 전공책 보신분", "컴퓨터공학부 전공책이고 안드로이드스튜디오 책이고 사진보시고 찾으시면 연락주세요 내일 모레 수업이라 꼭 찾아야해요", "2023-12-09 10:20:00", "도서관", PostItem.getAndroidImage()));
        originalDataList.add(new PostItem("인성관 가방", "뉴발란스 까만 가방 인성관 앞에 잠깐 놔뒀는데 없어졌습니다 가방사진 올립니다 보신분은 연락주시면 감사하겠습니다 가방 꼭 찾고싶습니다 연락주세요!", "2023-12-08 18:45:00", "인성관", PostItem.getBagImage()));
        originalDataList.add(new PostItem("기숙사 카드키", "혹시 지혜관 강의실이나 엘리베이터에서 기숙사 카드키 보신분 계신가요ㅜ 카드키 못찾으면 기숙사에 못들어갑니다ㅜㅜ", "2023-12-11 18:48:00", "지혜관", PostItem.getCardKeyImage()));
        originalDataList.add(new PostItem("뷰리 인형", "본부동에서 수업 듣고 셔틀버스 탔는데 뷰리 인형이 없어진걸 알았습니다 뷰리인형 본부동에서 발견하신 분 찾습니다ㅜ 혹시 모를까봐 뷰리 사진 올립니다", "2023-12-14 09:15:00", "본부동", PostItem.getBuriImage()));

        // 어댑터 초기화
        adapter = new CustomAdapter(LookPage.this, originalDataList);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 리스트뷰 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 아이템의 정보 가져오기
                PostItem selectedItem = originalDataList.get(position);

                // 상세 페이지로 전환하는 Intent 생성
                Intent intent = new Intent(LookPage.this, DetailActivity.class);
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
                // UI 갱신은 메인 스레드에서 수행되어야 함
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(filteredDataList);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
