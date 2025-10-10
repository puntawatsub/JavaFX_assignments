package task1;

public class PalindromeChecker {

    public PalindromeChecker() {}

    /**
     * Check if the {@code String s} is a palindrome
     * @param s {@code String} value to check
     * @return true if {@code String s} is a palindrome
     */
    public boolean isPalindrome(String s) {

        // filter out unnecessary chars
        String tempInput = s
                .replaceAll("\\s", "")
                .replaceAll(",", "")
                .replaceAll("\\.", "")
                .toLowerCase();

        // create char array from String
        char[] charArray = tempInput.toCharArray();

        // use string builder to form a reversed string
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = charArray.length - 1; i >= 0; i--) {
            stringBuilder.append(charArray[i]);
        }

        // check if the reversed String equals original string, return result
        return stringBuilder.toString().equals(tempInput);
    }

}
