public class Passenger {
    private Integer passengerId;
    private Integer survived;
    private Integer pClass;
    private String name;
    private String sex;
    private Float age;
    private Integer sibSp;
    private Integer parch;
    private String ticket;
    private Float fare;
    private String cabin;
    private String embarked;
    public String getFormattedName() {
        String result = "";
        result += this.name.substring(this.name.indexOf("."));
        result += this.name.substring(0, this.name.indexOf(","));
        return result;
    }
    public Passenger(String line) {
        if (line != null) {
            String[] details = line.split(",");

            if (details[Constants.ID_INDEX].equals("")) {
                this.passengerId = null;
            } else {
                this.passengerId = Integer.parseInt(details[Constants.ID_INDEX]);
            }
            if (details[Constants.SURVIVED_INDEX].equals("")) {
                this.survived = null;
            } else {
                this.survived = Integer.parseInt(details[Constants.SURVIVED_INDEX]);
            }
            if (details[Constants.CLASS_INDEX].equals("")) {
                this.pClass = null;
            } else {
                this.pClass = Integer.parseInt(details[Constants.CLASS_INDEX]);
            }

            if (details[Constants.LAST_NAME].equals("")) {
                this.name = null;
            } else {
                this.name = details[Constants.LAST_NAME] + "," + details[Constants.FIRST_NAME_INDEX];
            }

            if (details[Constants.SEX_INDEX].equals("")) {
                this.sex = null;
            } else {
                this.sex = details[Constants.SEX_INDEX];
            }
            if (details[Constants.AGE_INDEX].equals("")) {
                this.age = null;
            } else {
                this.age = Float.valueOf(details[Constants.AGE_INDEX]);
            }
            if (details[Constants.SIP_SP_INDEX].equals("")) {
                this.sibSp = null;
            } else {
                this.sibSp = Integer.parseInt(details[Constants.SIP_SP_INDEX]);
            }
            if (details[Constants.PARCH_INDEX].equals("")) {
                this.parch = null;
            } else {
                this.parch = Integer.parseInt(details[Constants.PARCH_INDEX]);
            }
            if (details[Constants.TICKET_NUM_INDEX].equals("")) {
                this.ticket = null;
            } else {
                this.ticket = details[Constants.TICKET_NUM_INDEX];
            }
            if (details[Constants.FARE_INDEX].equals("")) {
                this.fare = null;
            } else {
                this.fare = Float.parseFloat(details[Constants.FARE_INDEX]);
            }
            if (details[Constants.CABIN_INDEX].equals("")) {
                this.cabin = null;
            } else {
                this.cabin = details[Constants.CABIN_INDEX];
            }
            if (details.length == 13) {
                if (details[Constants.EMBARKED_INDEX].equals("")) {
                    this.embarked = null;
                } else {
                    this.embarked = details[Constants.EMBARKED_INDEX];
                }
            }
        }
    }

    public String convertToCSV() {
        return this.passengerId + "," + this.survived + "," + this.pClass + "," + this.name + "," + this.sex
                + "," + this.age + "," + this.sibSp + "," + this.parch + "," + this.ticket + "," + this.fare
                + "," + this.cabin + "," + this.embarked;


    }

    private int checkIntNum(String number) {
        int num = Constants.ZERO_VALUE;
        if (!number.equals(Constants.EMPTY_STRING)) {
            num = Integer.parseInt(number);
        }
        return num;
    }

    private float checkFloat(String number) {
        float check = Constants.ZERO_VALUE;
        if (!number.equals(Constants.EMPTY_STRING)) {
            check = Float.parseFloat(number);
        }
        return check;
    }

    public boolean survived() {
        boolean result = false;
        if (this.survived != null) {
            result = this.survived == Constants.SURVIVED;
        }
        return result;
    }

    public String getFormattedName(String name) {
        String formattedName = Constants.EMPTY_STRING;
        if (name != null) {
            String lastName = name.substring(1, name.indexOf(", "));
            String firstName = name.substring(name.indexOf(". ") + 2, name.length() - 1);
            formattedName = firstName + " " + lastName;
        }
        return formattedName;
    }

    public boolean validatePClass(int pClass) {
        boolean result = false;
        if (this.pClass != null) {
            if (pClass == Constants.ALL_INDEX) {
                result = true;
            } else {
                result = this.pClass == pClass;
            }

        }
        return result;
    }
    public boolean validateTicketNumber(String ticket) {
        boolean result = false;
        if (this.ticket != null) {
            if (!ticket.equals(Constants.EMPTY_STRING)) {
                result = this.ticket.contains(ticket);
            }else {
                result = true;
            }
        }
        return result;
    }
    public boolean validateMinIdAndMax(String min, String max) {
        boolean result = false;
        if (this.passengerId != null) {
            if (!min.equals(Constants.EMPTY_STRING) && !max.equals(Constants.EMPTY_STRING)) {
                result = this.passengerId >= Integer.parseInt(min) && this.passengerId <= Integer.parseInt(max);
            } else if (min.equals(Constants.EMPTY_STRING) && !max.equals(Constants.EMPTY_STRING)) {
                result = this.passengerId <= Integer.parseInt(max);
            } else if (!min.equals(Constants.EMPTY_STRING) && max.equals(Constants.EMPTY_STRING)) {
                result = this.passengerId >= Integer.parseInt(max);
            }else {
                result = true;
            }

        }
        return result;
    }


    public boolean validatePassengerName(String name) {
        boolean result = false;
        if (this.name != null) {
            if (!name.equals(Constants.EMPTY_STRING)) {
                result = this.name.contains(name);
            }else {
                result = true;
            }
        }
        return result;
    }

    public boolean validateParchAmount(String amount) {
        boolean result = false;
        if (this.parch != null) {
            if (!amount.equals(Constants.EMPTY_STRING)) {
                result = this.parch == Integer.parseInt(amount);
            }else {
                result = true;
            }
        }
        return result;
    }

    public boolean validateSibSPAmount(String amount) {
        boolean result = false;
        if (this.sibSp != null) {
            if (!amount.equals(Constants.EMPTY_STRING) ) {
                result = this.sibSp.equals(Integer.parseInt(amount)) ;
            }else {
                result = true;
            }
        }
        return result;
    }

    public boolean validateCabinNumberData(String cabin) {
        boolean result = false;
        if (this.cabin != null) {
            if (!cabin.equals(Constants.EMPTY_STRING)) {
                result = this.cabin.contains(cabin);
            }else {
                result = true;
            }
        }
        return result;
    }

    public boolean validateSex(int toCheck) {
        boolean validateSex = false;
        if (this.sex != null) {
            if (toCheck == Constants.ALL_INDEX)
            {
                validateSex = true;
            }else if (toCheck == Constants.MALE_OPTION_INDEX) {
                validateSex = this.sex.equals(Constants.SEX_OPTIONS[Constants.MALE_OPTION_INDEX]);
            } else if (toCheck == Constants.FEMALE_OPTION_INDEX) {
                validateSex = this.sex.equals(Constants.SEX_OPTIONS[Constants.FEMALE_OPTION_INDEX]);
            }
        }
        return validateSex;
    }

    public boolean validateEmbarked(int toCheck) {
        boolean validateEmbarked = false;
        if (this.embarked != null) {
            if (toCheck == Constants.ALL_INDEX)
            {
                validateEmbarked=true;
            }else if (toCheck == Constants.S_OPTION_INDEX) {
                validateEmbarked = this.embarked.equals(Constants.EMBARKED_OPTIONS[Constants.S_OPTION_INDEX]);
            } else if (toCheck == Constants.C_OPTION_INDEX) {
                validateEmbarked = this.embarked.equals(Constants.EMBARKED_OPTIONS[Constants.C_OPTION_INDEX]);
            } else if (toCheck == Constants.Q_OPTION_INDEX) {
                validateEmbarked = this.embarked.equals(Constants.EMBARKED_OPTIONS[Constants.Q_OPTION_INDEX]);
            }
        }

        return validateEmbarked;
    }
    public boolean validFare(String min, String max) {//האם הטווח של הנוסע בכרטיס
        boolean result = false;
        if (this.fare != null) {
            if (!min.equals(Constants.EMPTY_STRING) && !max.equals(Constants.EMPTY_STRING)) {
                result = this.fare >= Float.parseFloat(min) && this.fare <= Float.parseFloat(max);
            } else if (min.equals(Constants.EMPTY_STRING) && !max.equals(Constants.EMPTY_STRING)) {
                result = this.fare <= Float.parseFloat(max);
            } else if (!min.equals(Constants.EMPTY_STRING) && max.equals(Constants.EMPTY_STRING)) {
                result = this.fare >= Float.parseFloat(max);
            }else {
                result = true;
            }
        }
        return result;
    }

    public boolean isHaveFamily() {
        boolean result = false;
        int sum = 0;
        if (this.sibSp != null) {
            sum += this.sibSp;
            result = sum > 0;
        }

        if (this.parch != null) {
            sum += this.parch;
            result = sum > 0;
        }
        return result;
    }

    public boolean ageInRange(int minAge, int maxAge) {
        boolean result = false;
        if (this.age != null) {
            result = this.age >= minAge && this.age <= maxAge;
        }
        return result;
    }

    public boolean costInRange(int minCost, int maxCost) {
        boolean result = false;
        if (this.fare != null) {
            result = this.fare >= minCost && this.fare <= maxCost;
        }
        return result;
    }
}