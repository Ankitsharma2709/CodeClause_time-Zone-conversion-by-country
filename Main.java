import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("enter local Date and time (yyyy-MM-dd  HH:mm): ");
        String inputDateTime = scan.nextLine();

        LocalDateTime localDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("Enter Country TIme Zone (e.g. America/New_York): ");
        String inputTimeZone = scan.nextLine();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(inputTimeZone));

        String outputDateTime = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss"));

        System.out.println("converted date and time: "+ outputDateTime);








    }
}