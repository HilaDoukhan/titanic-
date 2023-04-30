import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Statistic {

        public Statistic( List<Passenger> passengers) {
            String toWrite = (byEmbarked(passengers)+"\n"+byAge(passengers)+"\n" +
                    byGender(passengers)+"\n"+bySibParch(passengers)+"\n"+byPrice(passengers)+"\n" +
                    byClass(passengers));

            File file = createFile(Constants.PATH_TO_STATISTIC_FILE);
            writeToTextFile(file,toWrite);
        }

    private void writeToTextFile(File file, String data) {//לכתוב לתוך הקובץ
        try {
            if (file != null && file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(data);
                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private File createFile(String path) {
        File file = new File(path);
        if (!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return file;
    }

        private String byClass(List<Passenger> passengers){
            String conclusion = "Total survived per class: ";
            for(int i = 1; i< Constants.PASSENGER_CLASS_OPTIONS.length; i++){
                float alive = 0, total = 0;
                for(Passenger p : passengers){
                    if(p.validatePClass(i)) {
                        total++;
                        if(p.survived()){
                            alive++;
                        }
                    }
                }
                conclusion+=Constants.PASSENGER_CLASS_OPTIONS[i]+" ---> "+String.format("%.2f", (alive/total)*100)+"%. ";
            }
            return conclusion;
        }
        private String byEmbarked(List<Passenger> passengers){
            String conclusion = "Total survived from embarked: ";
            for(int i = 1; i< Constants.EMBARKED_OPTIONS.length; i++){
                float alive = 0, total = 0;
                for(Passenger p : passengers){
                    if(p.validateEmbarked(i)) {
                        total++;
                        if(p.survived()){
                            alive++;
                        }
                    }
                }
                conclusion+=Constants.EMBARKED_OPTIONS[i]+" ---> "+String.format("%.2f", (alive/total)*100)+"%. ";
            }
            return conclusion;
        }
        private String byPrice(List<Passenger> passengers){
            String conclusion = "Total survived per price: ";
            for(int i = 0; i< Constants.PRICE.length-1; i+=2){
                float alive = 0, total = 0;
                for(Passenger p : passengers){
                    if(p.validFare(String.valueOf(Constants.PRICE[i]), String.valueOf(Constants.PRICE[i+1]))) {
                        total++;
                        if(p.survived()){
                            alive++;
                        }
                    }
                }
                conclusion+=Constants.PRICE[i]+"-"+ Constants.PRICE[i+1]+" ---> "+String.format("%.2f", (alive/total)*100)+"%. ";
                if(Constants.PRICE[i+1] == Integer.MAX_VALUE){
                    conclusion = conclusion.replaceAll("-2147483647", "+");
                }
            }
            return conclusion;
        }

        private String bySibParch(List<Passenger> passengers){
            float hadFamilyTotal= 0,hadFamilyAlive = 0,noFamilyTotal = 0,noFamilyAlive = 0;
            for(Passenger p :passengers){
                if(p.isHaveFamily()){
                    hadFamilyTotal++;
                    if(p.survived()){
                        hadFamilyAlive++;
                    }
                }else{
                    noFamilyTotal++;
                    if(p.survived()){
                        noFamilyAlive++;
                    }
                }
            }
            String formattedDotFamily = String.format("%.2f",(hadFamilyAlive/hadFamilyTotal)*100);
            String formattedDotNoFamily = String.format("%.2f",(noFamilyAlive/noFamilyTotal)*100);
            return "Total people without family who survived are "+formattedDotNoFamily+"%.\n"+
                    "Total family with family who survived are "+formattedDotFamily+"%.";
        }

        private String byAge(List<Passenger> passenger){
            String calculated = "Survived by age ";
            for(int i = 0;i<=Constants.AGES.length-1;i+=2){
                float alive = 0,total = 0;
                int minAge = Constants.AGES[i];
                int maxAge = Constants.AGES[i+1];
                for(Passenger p : passenger){
                    if(p.ageInRange(minAge, maxAge)){
                        total++;
                        if(p.survived()){
                            alive++;
                        }
                    }
                }
                String formattedDot = String.format("%.2f", (alive/total)*100);
                calculated+= minAge+"-"+maxAge+" ---> "+formattedDot+"%. ";
                if(maxAge == Constants.AGES[Constants.AGES.length-1]){
                    calculated = calculated.replaceAll("-"+String.valueOf(Constants.AGES[Constants.AGES.length-1]), "+");
                }
            }
            return calculated;
        }
        private String byGender(List<Passenger> passengers){
            float totalMale = 0,totalFemale = 0,survivedMale = 0,survivedFemale = 0;
            for(int i = 0;i< passengers.size();i++){
                if(passengers.get(i).validateSex(Constants.MALE_OPTION_INDEX)){
                    totalMale++;
                    if(passengers.get(i).survived()){
                        survivedMale++;
                    }
                }else{
                    totalFemale++;
                    if(passengers.get(i).survived()){
                        survivedFemale++;
                    }
                }
            }
            String formattedDotFemale= String.format("%.2f", (survivedFemale/totalFemale)*100);
            String formattedDotMale= String.format("%.2f", (survivedMale/totalMale)*100);
            return "Total male survived "+formattedDotMale+"%, Total female survived "+formattedDotFemale+"%";
        }
    }

