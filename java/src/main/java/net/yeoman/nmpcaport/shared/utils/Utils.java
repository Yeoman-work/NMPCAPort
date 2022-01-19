package net.yeoman.nmpcaport.shared.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Component
public class   Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurtuvwxyz";
    private final List<String> senateDistricts = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5" ,"6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42"));

    private final  List<String> houseDistricts = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5" ,"6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53",
            "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66",
            "67", "68", "69", "70"));

    private final List<String> congressionalDistricts = new ArrayList<>(Arrays.asList("1", "2", "3"));

    private final List<String> legislativeStatus = new ArrayList<>(Arrays.asList( "in committee", "passed", "vote scheduled", "voted down" ));

    private final List<String> governorStatus = new ArrayList<>(Arrays.asList("awaiting bill", "signed", "veto"));



    public LocalDate initialStateSenateTerm(){

        LocalDate current = LocalDate.now();
        LocalDate initialDate = LocalDate.of(2024, Month.NOVEMBER, 1);
        LocalDate election = initialDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        if(current.isAfter(election)){
            election = election.plusYears(4);
        }

        return election;
    }

    //initial date 2 year cycle
    public LocalDate initial2yearCycle(){

        LocalDate currentDate = LocalDate.now();
        if(currentDate.getYear() % 2 != 0){
            currentDate = currentDate.plusYears(1);
        }

        LocalDate election = LocalDate.of(currentDate.getYear(), Month.NOVEMBER, 1);

        election = election.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        return election;
    }


    //reset congressional term
    public LocalDate CongressionalReelection(LocalDate date){

        int year = date.getYear() + 2;

        LocalDate newElectionDate = LocalDate.of(date.getYear(), Month.NOVEMBER, 1 );

        LocalDate reelectionDate = newElectionDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        return reelectionDate;
    }

    //resetGovernor term
    public LocalDate GovernorReelection(LocalDate date){

        LocalDate newElectionDate = LocalDate.of(date.getYear() + 4, Month.NOVEMBER, 1);

        LocalDate reelectionDate = newElectionDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        return reelectionDate;
    }

    //reset Senator term
    public LocalDate UsSenatorReelection(LocalDate date){

        LocalDate reelectionDate = LocalDate.of(date.getYear() + 6, Month.NOVEMBER, 1);

        reelectionDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        return reelectionDate;
    }

    // is a congressional district
    public Boolean isCongressionalDistrict(String district){

        return congressionalDistricts.contains(district);
    }

    // is a house district
    public Boolean isNMHouseDistrict(String district){

        return houseDistricts.contains(district);
    }

    // is a senate district
    public Boolean isSenateDistrict(String district){

        return senateDistricts.contains(district);
    }

    //generate random Id
    public String generateRandomID(){

        return generateRandomString(30);
    }

    public String generateRandomString(int length){

        StringBuilder returnValue = new StringBuilder();

        for(int i = 0; i < length; i++){

            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public List<String> getLegislativeStatus(){

        return legislativeStatus;
    }

    public List<String> getGovernorStatus(){

        return governorStatus;
    }

    public Boolean ListNotNull( Object testList ){

        Boolean isValid = false;

        if(testList != null){

                isValid = true;
        }

        return isValid;
    }

    public ModelMapper objectMapper(){

        return new ModelMapper();
    }
}
