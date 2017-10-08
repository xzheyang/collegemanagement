package www.lesson.common.utils;

public class ExcelFomart {

    public static boolean excelUserFomart(String sex, String birthday,String identification){

        //准备校验username和password的格式
        String regexSex = "[0-1]{1}";
        String regexBirthday="^(19|20)\\d{2}/(1[0-2]|0?[1-9])/(0?[1-9]|[1-2][0-9]|3[0-1])$";
        String regexIdentification="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";



        if(sex.matches(regexSex)&&birthday.matches(regexBirthday)&&
                identification.matches(regexIdentification)
                )
        {
            return true;
        }




        return false;
    }



    public static boolean excelYearFomart(String year){


        String regexYear="^(19|20)\\d{2}";




        if(year.matches(regexYear))
        {
            return true;
        }


        return false;
    }


    public static boolean excelIdFomart(String id){


        String regexId="\\w{1,16}";   //id长度不能大于16位,且必须不能有字母数字和下划线以外的字符




        if(id.matches(regexId))
        {
            return true;
        }


        return false;
    }


    //全中文字符检测
    public static boolean excelChineseCharactersFoamrt(String Characters){
        String regexChineseCharacters="[\\u4e00-\\u9fa5]+";

        if(Characters.matches(regexChineseCharacters))
        {
            return true;
        }


        return false;
    }

    public static boolean excelLessonFomart(String name,String session){

        //学期格式只能为20xx/03/1,或19xx/09/1
        String regexSession="^(19|20)\\d{2}/(0?3|0?9)/(0?1)$";


        if(excelChineseCharactersFoamrt(name)&&session.matches(regexSession)){
            return true;
        }

        return false;
    }










}
