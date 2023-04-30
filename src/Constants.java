public class Constants {
    public static final String PATH_TO_DATA_FILE = "src/data/titanic.csv";

    public static final String PATH_TO_STATISTIC_FILE = "src/data/statistic.txt";

    public static final String PATH_TO_FILTER_DATA = "src/data/", END_CSV_FILE = ".csv";
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 700;
    public static final String[] PASSENGER_CLASS_OPTIONS = { "All", "1st", "2nd", "3rd"};

    public static final String [] EMBARKED_OPTIONS = {"All" , "S" ,"C","Q"};

    public static final String[] SEX_OPTIONS = { "All", "Male", "Female"};

    public static final int MARGIN_FROM_TOP = 10;
    public static final int MARGIN_FROM_LEFT = 5;
    public static final int LABEL_WIDTH = 150;
    public static final int LABEL_HEIGHT = 30;
    public static final int COMBO_BOX_WIDTH = 80;
    public static final int COMBO_BOX_HEIGHT = 30;

    public static final int SURVIVED = 1;

    public static final int DEAD = 0;

    public static final int FIRST_CLASS = 1;

    public static final int SECOND_CLASS = 2;

    public static final int THIRD_CLASS = 3;

    public static final int S_OPTION_INDEX = 1, C_OPTION_INDEX = 2, Q_OPTION_INDEX = 3, ALL_INDEX = 0;

    public static final int MALE_OPTION_INDEX = 1, FEMALE_OPTION_INDEX = 2;

    public static final int ID_INDEX = 0 , SURVIVED_INDEX=1 , CLASS_INDEX=2 , LAST_NAME=3 ,
            FIRST_NAME_INDEX=4, SEX_INDEX=5 , AGE_INDEX=6 , SIP_SP_INDEX=7 , PARCH_INDEX=8 ,
            TICKET_NUM_INDEX=9 , FARE_INDEX=10 , CABIN_INDEX=11 , EMBARKED_INDEX=12;

    public static final int MAX_PASSENGER = 891 , MIN_PASSENGER = 1;

    public static final int TEXT_FILED_WIDTH = 50,TEXT_FILED_HEIGHT=25;

    public static final int BUTTON_WIDTH =150, BUTTON_HEIGHT=30; //אורך הכפתור
    public static final int ZERO_VALUE = 0;
    public static final int DEFAULT_VALUE = -1;
    public static final String EMPTY_STRING = "";
    public static final int[] PRICE = {0, 10, 11, 30, 31, Integer.MAX_VALUE};
    public static final int[] AGES = {0, 10, 11, 20, 21, 30, 31, 40, 41, 50, 51, 120};
}
