package com.example.ictapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ResultActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    RecyclerView menuRecyclerView;
    MenuAdapter menuAdapter;
    List<MenuItem> menuList;
    Button backButton;
    Button randomBtn;
    TextView tasteTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 뷰 연결
        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        backButton = findViewById(R.id.backButton);
        randomBtn = findViewById(R.id.randomBtn);
        tasteTimeText = findViewById(R.id.tasteTimeText);

        // Intent로 전달된 값 받기
        Intent intent = getIntent();
        String taste = intent.getStringExtra("taste");
        String time = intent.getStringExtra("time");
        String weather = intent.getStringExtra("weather");
        String source = intent.getStringExtra("source");

        // 텍스트 설정
        if (taste != null && time != null) {
            tasteTimeText.setText(taste + " - " + time + " 추천");
        } else if (weather != null) {
            tasteTimeText.setText(weather + " 추천");
        } else {
            tasteTimeText.setText("추천 결과");
        }

        // "이전으로 돌아가기" 버튼 기능
        backButton.setOnClickListener(v -> {
            Intent backIntent;
            if ("weather".equals(source)) {
                backIntent = new Intent(ResultActivity.this, WeatherActivity.class);
            } else {
                backIntent = new Intent(ResultActivity.this, TasteTimeActivity.class);
            }
            backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(backIntent);
            finish();
        });

        // 랜덤 추천 버튼 클릭 시
        randomBtn.setOnClickListener(v -> {
            if (menuList != null && !menuList.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(menuList.size());
                MenuItem item = menuList.get(index);
                showLikeDialog(item);
            } else {
                Toast.makeText(ResultActivity.this, "추천 가능한 음식이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 메뉴 리스트 생성 및 조건별 추가
        menuList = new ArrayList<>();

        if ("단맛".equals(taste) && "아침".equals(time)) {
            menuList.add(new MenuItem("고구마스틱", "sweet_potato"));
            menuList.add(new MenuItem("꿀토스트", "honey_toast"));
        } else if ("단맛".equals(taste) && "점심".equals(time)) {
            menuList.add(new MenuItem("꿀떡", "honey_rice_cake"));
            menuList.add(new MenuItem("꿀호떡", "honey_hotteok"));
            menuList.add(new MenuItem("허니버터치킨", "honey_butter_chicken"));
        } else if ("단맛".equals(taste) && "저녁".equals(time)) {
            menuList.add(new MenuItem("허니브레드", "honey_bread"));
            menuList.add(new MenuItem("허니갈릭치킨", "honey_garlic_chicken"));
            menuList.add(new MenuItem("허니감자튀김", "honey_french_fries"));
        } else if ("짠맛".equals(taste) && "아침".equals(time)) {
            menuList.add(new MenuItem("계란죽", "egg_porridge"));
            menuList.add(new MenuItem("미역국", "seaweed_soup"));
            menuList.add(new MenuItem("소금토스트", "salty_toast"));
        } else if ("짠맛".equals(taste) && "점심".equals(time)) {
            menuList.add(new MenuItem("김치찌개", "kimchi"));
            menuList.add(new MenuItem("제육볶음", "pork"));
            menuList.add(new MenuItem("된장찌개", "soybean_stew"));
        } else if ("짠맛".equals(taste) && "저녁".equals(time)) {
            menuList.add(new MenuItem("감자탕", "gamjatang"));
            menuList.add(new MenuItem("돼지국밥", "pork_soup"));
            menuList.add(new MenuItem("어묵탕", "fish_cake_stew"));
        } else if ("매운맛".equals(taste) && "아침".equals(time)) {
            menuList.add(new MenuItem("매운북엇국", "spicy_dried_pollack_soup"));
        } else if ("매운맛".equals(taste) && "점심".equals(time)) {
            menuList.add(new MenuItem("떡볶이", "tteokbokki"));
            menuList.add(new MenuItem("매운제육", "spicy_pork"));
            menuList.add(new MenuItem("매운우동", "spicy_udon"));
        } else if ("매운맛".equals(taste) && "저녁".equals(time)) {
            menuList.add(new MenuItem("불닭볶음면", "buldak"));
            menuList.add(new MenuItem("매운짬뽕", "spicy_jjamppong"));
            menuList.add(new MenuItem("매운닭발", "spicy_chicken_feet"));
        } else if ("담백한 맛".equals(taste) && "아침".equals(time)) {
            menuList.add(new MenuItem("두부미소국", "tofu_miso_soup"));
            menuList.add(new MenuItem("야채죽", "vegetable_porridge"));
            menuList.add(new MenuItem("계란찜", "steamed_egg"));
        } else if ("담백한 맛".equals(taste) && "점심".equals(time)) {
            menuList.add(new MenuItem("삼계탕", "samgyetang"));
            menuList.add(new MenuItem("닭가슴살 샐러드", "chicken_salad"));
            menuList.add(new MenuItem("연두부 샐러드", "soft_tofu_salad"));
        } else if ("담백한 맛".equals(taste) && "저녁".equals(time)) {
            menuList.add(new MenuItem("버섯덮밥", "mushroom_rice"));
            menuList.add(new MenuItem("생선구이", "grilled_fish"));
            menuList.add(new MenuItem("나물비빔밥", "namul_bibimbap"));
        }

        if ("맑음".equals(weather)) {
            menuList.add(new MenuItem("김밥", "gimbab"));
            menuList.add(new MenuItem("유부초밥", "yubu_sushi"));
            menuList.add(new MenuItem("샌드위치", "sandwich"));
        } else if ("비".equals(weather)) {
            menuList.add(new MenuItem("파전", "pajun"));
            menuList.add(new MenuItem("부대찌개", "bude"));
            menuList.add(new MenuItem("감자전", "potato_pancake"));
        } else if ("눈".equals(weather)) {
            menuList.add(new MenuItem("붕어빵", "fishbread"));
            menuList.add(new MenuItem("군고구마", "roasted_sweet_potato"));
            menuList.add(new MenuItem("꿀호떡", "honey_hotteok"));
        } else if ("더움".equals(weather)) {
            menuList.add(new MenuItem("냉면", "coldnoodle"));
            menuList.add(new MenuItem("콩국수", "soybean_noodle"));
            menuList.add(new MenuItem("메밀소바", "soba"));
        } else if ("추움".equals(weather)) {
            menuList.add(new MenuItem("어묵탕", "fish_cake_stew"));
            menuList.add(new MenuItem("갈비탕", "galbitang"));
            menuList.add(new MenuItem("떡국", "tteokguk"));
            menuList.add(new MenuItem("다코야끼", "dako"));
        }

        // 정렬 및 어댑터 설정
        Collections.sort(menuList, (a, b) -> b.getLikeCount() - a.getLikeCount());
        menuAdapter = new MenuAdapter(menuList, this);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onItemClick(MenuItem item) {
        showLikeDialog(item);
    }

    private void showLikeDialog(MenuItem item) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_random_recommend, null);
        ImageView foodImage = dialogView.findViewById(R.id.randomFoodImage);
        TextView foodName = dialogView.findViewById(R.id.randomFoodName);
        Button likeBtn = dialogView.findViewById(R.id.likeBtn);
        Button dislikeBtn = dialogView.findViewById(R.id.dislikeBtn);

        foodName.setText(item.getName());
        int resId = item.getImageResId(this);
        if (resId != 0) {
            foodImage.setImageResource(resId);
        } else {
            foodImage.setImageResource(R.drawable.default_food);
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        likeBtn.setOnClickListener(view -> {
            item.increaseLike();
            Toast.makeText(this, "좋아요 +1!", Toast.LENGTH_SHORT).show();
            Collections.sort(menuList, (a, b) -> b.getLikeCount() - a.getLikeCount());
            menuAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });

        dislikeBtn.setOnClickListener(view -> {
            Toast.makeText(this, "다음에 더 맛있는 걸 추천할게요!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}