import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    private static int csvNumbers = 0;
    private List<Passenger> filterData;
    private JLabel answerLabel;
    public Filter(JLabel answer, List<Passenger> passengers, int classNumber, int genderData, int embarked,
                  String passengerNumberMinData, String passengerNumberMaxData, String passengerNameData,
                  String parchAmountData, String sibSPAmountData, String ticketNumberData, String maxTicketPriceData,
                  String minTicketPriceData, String cabinNumberData) {


        List<Passenger> filteredPassengers = new ArrayList<>();

        filteredPassengers = filterByClass(classNumber,passengers );
        filteredPassengers = filterByGender(genderData, filteredPassengers);
        filteredPassengers = filterById(passengerNumberMinData, passengerNumberMaxData, filteredPassengers);
        filteredPassengers =filterByEmbarked(embarked, filteredPassengers);
        filteredPassengers = filterByName(passengerNameData,filteredPassengers);
        filteredPassengers = filterByParch(parchAmountData ,filteredPassengers);
        filteredPassengers = filterBySibSp(sibSPAmountData,filteredPassengers);
        filteredPassengers = filterByTicket(ticketNumberData,filteredPassengers);
        filteredPassengers = filterByFare(minTicketPriceData,maxTicketPriceData,filteredPassengers);
        filteredPassengers = filterByCabin(cabinNumberData,filteredPassengers);

        this.filterData = filteredPassengers;
        this.answerLabel=answer;
        createFileAndAnswer();

    }
    private List<Passenger> filterByEmbarked(int embarkSelected ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (embarkSelected != Constants.ALL_INDEX)
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateEmbarked(embarkSelected))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterByClass(int classSelected ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (classSelected != Constants.ALL_INDEX)
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validatePClass(classSelected))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }

    private List<Passenger> filterById(String min ,String max,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (min != Constants.EMPTY_STRING && max != Constants.EMPTY_STRING)
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateMinIdAndMax(min,max))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }

    private List<Passenger> filterByGender(int genderSelected ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (genderSelected != Constants.ALL_INDEX)
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateSex(genderSelected))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }
        return result;
    }

    private List<Passenger> filterByName(String name ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!name.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validatePassengerName(name))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterByParch(String parch ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!parch.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateParchAmount(parch))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterBySibSp(String sibSp ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!sibSp.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateSibSPAmount(sibSp))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterByTicket(String ticket ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!ticket.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateTicketNumber(ticket))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterByFare(String min ,String max,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!min.equals(Constants.EMPTY_STRING) && !max.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validFare(min,max))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private List<Passenger> filterByCabin(String cabin ,List<Passenger> passengers)
    {
        List<Passenger> result = new ArrayList<>();
        if (!cabin.equals(Constants.EMPTY_STRING))
        {
            for ( int i = 0; i < passengers.size(); i++)
            {
                if (passengers.get(i).validateCabinNumberData(cabin))
                {
                    result.add(passengers.get(i));
                }
            }
        }else {
            result = passengers;
        }

        return result;
    }
    private void createFileAndAnswer()  {
        int survived = 0;
        int died = 0;
        for (int i = 0; i < this.filterData.size(); i++) {
            if (this.filterData.get(i).survived()) {
                survived++;
            } else {
                died++;
            }
        }
        csvNumbers++;
        String fileName = Constants.PATH_TO_FILTER_DATA + csvNumbers + Constants.END_CSV_FILE;
        File file = createFile(fileName );
        sortByName(this.filterData);
        writePassengerToCSVFile(file, this.filterData);
        this.answerLabel.setText("Total rows: " + this.filterData.size() + " (" + survived + " survived, " + died + " did not)");
    }

    private void sortByName(List<Passenger> passengers)
    {
        for (int i = 0; i<passengers.size(); i++)
        {
            for (int j = i+1; j<passengers.size(); j++)
            {
                String nameIndexI = passengers.get(i).getFormattedName();
                String nameIndexJ = passengers.get(j).getFormattedName();
                if (nameIndexI!=null && nameIndexJ!=null)
                {
                    if (nameIndexI.compareTo(nameIndexJ) >0)
                    {
                        Passenger tempIndexI = passengers.get(i);
                        Passenger tempIndexJ = passengers.get(j);
                        passengers.set(i,tempIndexJ);
                        passengers.set(j, tempIndexI);

                    }
                }

            }
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

    private void writePassengerToCSVFile(File file, List<Passenger> data) {
        try {
            if (file != null && file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i< data.size(); i++)
                {
                    bufferedWriter.write(data.get(i).convertToCSV());
                    bufferedWriter.write("\n");
                }
                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}



