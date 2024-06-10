import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String text = "";
        try (FileReader fr = new FileReader("C:\\Users\\Andre\\IdeaProjects\\pr1\\src\\index.html")) {
            int code = -1;
            while ((code = fr.read()) != -1){
                sb.append(Character.toChars(code));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        text = sb.toString();

        Pattern pattern = Pattern.compile("<([a-z]+)>(\\s*\\n)*\\s*</\\1>");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String tag = matcher.group(0);
            sb.replace(sb.indexOf(tag), sb.indexOf(tag) + tag.length(), "");
            text = sb.toString();
        }

        pattern = Pattern.compile("\\s*\\n");
        matcher = pattern.matcher(text);

        while (matcher.find()){
            String tag = matcher.group(0);
            sb.replace(sb.indexOf(tag), sb.indexOf(tag) + tag.length(), "\n");
            text = sb.toString();
        }

        try (FileWriter fw = new FileWriter("C:\\Users\\Andre\\IdeaProjects\\pr1\\src\\update-index.html")) {
            for (int i = 0; i < text.length(); i++){
                fw.write(text.charAt(i));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}