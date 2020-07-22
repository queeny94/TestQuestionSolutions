public class Problem2 {

    public static void main(String[] args) {
        
        if (args.length > 0 && args[0] != null){
            try{
                Integer inputValue = Integer.parseInt(args[0]);

                int sumOfDigit = String.valueOf(inputValue).chars().map(Character::getNumericValue).sum();

                System.out.println(sumOfDigit);
            }catch(NumberFormatException ex){
                System.out.println("Input Argument is not number.");
            }
        }
        else{
            System.out.println("One integer number need to provide in command line.");
        }
        

    }
    
}