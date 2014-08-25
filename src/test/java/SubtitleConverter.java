import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SubtitleConverter {

    @Test
    public void convertSubtitle() throws IOException, ParseException {
        long addMillis = -10000;

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss,SSS");
        String re1="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec 1
        String re2="(.)";	// Any Single Character 1
        String re3="(\\d)";	// Any Single Digit 1
        String re4="(\\d)";	// Any Single Digit 2
        String re5="(\\d)";	// Any Single Digit 3
        String re6="(\\s+)";	// White Space 1
        String re7="(.)";	// Any Single Character 2
        String re8="(.)";	// Any Single Character 3
        String re9="(.)";	// Any Single Character 4
        String re10="(\\s+)";	// White Space 2
        String re11="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec 2
        String re12="(.)";	// Any Single Character 5
        String re13="(\\d)";	// Any Single Digit 4
        String re14="(\\d)";	// Any Single Digit 5
        String re15="(\\d)";	// Any Single Digit 6

        String patternStr = re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15;
        Pattern p = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        BufferedReader fileReader = new BufferedReader(new FileReader("/home/jos/Videos/films/Jack.The.Giant.Slayer.2013.DVDRip.XviD-RARBG/Jack.The.Giant.Slayer.2013.Ned_DVD(25fps).srt"));

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("/tmp/jack.srt"));

        for(String line; (line = fileReader.readLine()) != null; ) {
            if (line.matches(patternStr)) {
                String [] parts = line.split(" ");
                Date date1 = sdf.parse(parts[0]);
                Date newDate1 = new Date(date1.getTime() + addMillis);
                String newDate1Str = sdf.format(newDate1);

                Date date2 = sdf.parse(parts[2]);
                Date newDate2 = new Date(date2.getTime() + addMillis);
                String newDate2Str = sdf.format(newDate2);

                fileWriter.write(newDate1Str + " --> " +  newDate2Str + "\n");
            } else {
                fileWriter.write(line + "\n");
            }
        }

        fileReader.close();
        fileWriter.flush();
        fileWriter.close();
    }
}
