package com.example.bank;


public class Methods {

    public void setColorTheme() {

        switch (Constant.color) {
            case 1:
                Constant.theme = R.style.AppTheme_red;
                break;
            case 2:
                Constant.theme = R.style.AppTheme_pink;
                break;
            case 3:
                Constant.theme = R.style.AppTheme_darpink;
                break;
            case 4:
                Constant.theme = R.style.AppTheme_violet;
                break;
            case 5:
                Constant.theme = R.style.AppTheme_blue;
                break;
            case 6:
                Constant.theme = R.style.AppTheme_skyblue;
                break;
            case 7:
                Constant.theme = R.style.AppTheme_green;
                break;
            case 8:
                Constant.theme = R.style.AppTheme_grey;
                break;
            case 9:
                Constant.theme = R.style.AppTheme_brown;
                break;
            default:
                Constant.theme = R.style.Base_Theme_Bank;
                break;
        }
    }
}
