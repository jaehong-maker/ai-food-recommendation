package com.example.ictapp;

import android.content.Context;

public class MenuItem {
    private String name;
    private String imageName; // 예: "tteokbokki" → R.drawable.tteokbokki
    private int likeCount = 0; // ✅ 좋아요 수

    public MenuItem(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    // ✅ 좋아요 수 반환
    public int getLikeCount() {
        return likeCount;
    }

    // ✅ 좋아요 1 증가
    public void increaseLike() {
        likeCount++;
    }

    // 이미지 리소스 ID 반환
    public int getImageResId(Context context) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}
