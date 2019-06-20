package com.maxwell.sreebagalamukhitv;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class StringConstants {

    public static String mainUrl="http://www.bagalamukhitv.com/SreeBag_Mgt12/api/";
    public static String videoUrl="view_videos.php";
    public static String inputLanguage="language";
    public static String loginUrl="login.php?login";
    public static String inputDeviceId="&device=";
    public static String inputRegToken="&token=";

    public static String bookAstrologicalPredictionUrl ="astrology_prediction.php";
    public static String bookAmavasyaYaagamUrl ="amavasya.php";
    public static String bookPournamiYaagamUrl ="pournami.php";

    public static String inputPredictionType="prediction";
    public static String inputAmount="amount";
    public static String inputName="name";
    public static String inputEmailID="emailid";
    public static String inputPhone="phone";
    public static String inputAddress="address";
    public static String inputgender="gender";
    public static String inputDOB="dob";
    public static String inputTOB="time_of_birth";
    public static String inputPOB="place_of_birth";
    public static String inputDistrict="district";
    public static String inputCountry="country";
    public static String inputRaasi="raasi";
    public static String inputStar="Star";
    public static String inputMarried="married";
    public static String inputSpouseName="spouse_name";
    public static String  inputSpouseDOB="spouse_dob";
    public static String  inputSpouseTOB="spouse_time_birth";
    public static String inputSpousePOB="spouse_place_of_birth";
    public static String inputSpouseDistrict="spouse_dstrict";
    public static String inputSpouseCountry="spouse_country";
    public static String inputSpouseRaasi="spouse_raasi";
    public static String inputSpouseStar="spouse_Star";
    public static String inputQuestion="question";

    public static String  inputNumberofPersons="number";
    public static String  inputTotalAmount="totalamount";
    public static String  inputstar="star";
    public static String  inputNatchathiram="nakshatram";
    public static String inputPurposeofyaagam="purpose_of_yaga";
    public static String inputMessage="Message";

    public static String ErrorMessage(VolleyError volleyError) {
        String message = null;
        if (volleyError instanceof NetworkError) {
            message = "Cannot connect to Internet...Please check your connection!";
// Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof ServerError) {
            message = "The server could not be found. Please try again after some time!!";
// Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof AuthFailureError) {
            message = "Cannot connect to Internet...Please check your connection!";
// Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof ParseError) {
            message = "Parsing error! Please try again after some time!!";
// Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof NoConnectionError) {
            message = "Cannot connect to Internet...Please check your connection!";
//Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof TimeoutError) {
            message = "Connection TimeOut! Please check your internet connection.";
//Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }

        //   Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        return message;
    }

}
