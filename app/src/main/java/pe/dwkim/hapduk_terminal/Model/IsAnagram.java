package pe.dwkim.hapduk_terminal.Model;

/**
 * Created by dwkim on 16. 6. 21..
 */
public class IsAnagram {
    public void main(String[] args){

    }

    private Boolean isAnagram(String s1, String s2){
        if(s1.length() < 1 && s1.length() != s2.length()){
            return false;
        }

        String passedString = "";

        for(int i=0; i < s1.length(); i++){
            if(!passedString.contains(s1.substring(i))) {
                int s1_contains_count = 0;
                int s2_contains_count = 0;

                for (int ii = 0; ii < s1.length(); ii++) {
                    if (s1.substring(i) == s1.substring(ii)) {
                        s1_contains_count = s1_contains_count + 1;
                    }
                }

                for (int y = 0; y < s2.length(); y++) {
                    if (s1.substring(i) == s2.substring(y)) {
                        s2_contains_count = s2_contains_count + 1;
                    }
                }

                if (s1_contains_count != s2_contains_count) {
                    return false;
                } else {
                    passedString = passedString + s1.substring(i);
                }
            }
        }

        return true;

    }
}