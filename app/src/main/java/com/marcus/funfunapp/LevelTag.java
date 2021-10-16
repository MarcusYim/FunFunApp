package com.marcus.funfunapp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LevelTag
{
    /*
    String levelId;
    String levelName;
    String levelImage;
    String levelBrief;
    String levelIntroduce;

    long levelPrice;
    int levelValidMonth;
     */
    Long validDate;
    public LevelTag() {

    }

    public LevelTag(Long validDate)
    {
        super();
        /*
        this.levelId = levelId;
        this.levelName = levelName;
        this.levelImage = levelImage;
        this.levelBrief = levelBrief;
        this.levelIntroduce = levelIntroduce;
        this.validDate = validDate;
        this.levelPrice = levelPrice;
        this.levelValidMonth = levelValidMonth;

         */
        this.validDate = validDate;
    }

    public Long getValidDate()
    {
        return validDate;
    }
}
