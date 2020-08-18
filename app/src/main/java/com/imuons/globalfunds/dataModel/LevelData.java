package com.imuons.globalfunds.dataModel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LevelData implements Serializable {
    @SerializedName("level_id")
    @Expose
    private int levelId;
    @SerializedName("level_name")
    @Expose
    private String levelName;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @NonNull
    @Override
    public String toString() {
        return levelName;

    }
}
