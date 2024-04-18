import java.util.Scanner;

class FirstNameAbsence extends Exception{
    public FirstNameAbsence(String message){
        super(message);
    }
}
class LastNameAbsence extends Exception{
    public LastNameAbsence(String message){
        super(message);
    }
}
class NoNamePresent extends Exception{
    public NoNamePresent(String message){
        super(message);
    }
}
class BlankEmailField extends Exception{
    public BlankEmailField(String message){
        super(message);
    }
}
class AbsenceofGmailSuffix extends Exception{
    public AbsenceofGmailSuffix(String message){
        super(message);
    }
}
class NotProperlyFormatedEmailPrefix extends Exception{
    public NotProperlyFormatedEmailPrefix(String message){
        super(message);
    }
}
class BlankNIDPassportField extends Exception{
    public BlankNIDPassportField(String message){
        super(message);
    }
}
class UnexpectedNIDFormat extends Exception{
    public UnexpectedNIDFormat(String message){
        super(message);
    }
}
class UnexpectedPassportFormat extends Exception{
    public UnexpectedPassportFormat(String message){
        super(message);
    }
}
class WrongBirthdateFormat extends Exception{
    public WrongBirthdateFormat(String message){
        super(message);
    }
}
class BirthdateBlank extends Exception{
    public BirthdateBlank(String message){
        super(message);
    }
}
class InvalidHouseNo extends Exception{
    public InvalidHouseNo(String message){
        super(message);
    }
}
class InvalidLocation extends Exception{
    public InvalidLocation(String message){
        super(message);
    }
}
class InvalidDivision extends Exception{
    public InvalidDivision(String message){
        super(message);
    }
}

class Name{
    String firstName;
    String middleName;
    String lastName;
    String fullName;
    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fullName = firstName + middleName + lastName;
    }
    public Name() {
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;    
        this.fullName = null;
    } 
    public Name(String fullname) {
        this.fullName = fullname;
        String[] nameArr = fullname.split(" ", 3);
        int len = nameArr.length;
        if(len == 3) {
            this.firstName = nameArr[0];
            this.middleName = nameArr[1];
            this.lastName = nameArr[2];
        }else if(len == 2) {
            this.firstName = nameArr[0];
            this.middleName = null;
            this.lastName = nameArr[1];
        } else if(len == 1) {
            this.firstName = nameArr[0];
            this.middleName = null;
            this.lastName = null;
        } else if(len == 0) {
            this.firstName = null;
            this.middleName = null;
            this.lastName = null;
        }
    } 

    public void validateName() throws FirstNameAbsence, LastNameAbsence, NoNamePresent{
        if(this.firstName==null || this.firstName.length() == 0) throw new FirstNameAbsence("Please, Provide First Name."); 
        if(this.lastName==null || this.firstName.length() == 0) throw new LastNameAbsence("Please, Provide Last Name."); 
        if((this.firstName==null || this.firstName.length() == 0) && (this.lastName==null || this.firstName.length() == 0)) throw new LastNameAbsence("Both the names are absent."); 
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
class Email{
    String email;

    public Email(String email) {
        this.email = email;
    }

    void validateMail() throws BlankEmailField, NotProperlyFormatedEmailPrefix, AbsenceofGmailSuffix{
        if(this.email == null) throw new BlankEmailField("Please, Provide mail.");
        if(!(this.email.endsWith("@gmail.com"))) throw new AbsenceofGmailSuffix("Only Gmail addresses are acceptable");
        boolean formatted = true;
        for(char c: this.email.toCharArray()){
            if(c=='@') break;
            if(!((c>='a' && c<='z') || (c>='0' && c<='9'))){
                formatted = false;
                break;
            } 
        }
        if(!formatted) throw new NotProperlyFormatedEmailPrefix("Email should contain only digits and lowercase letters.");
    }
}
class NIDPassport{
    String nid;
    String passport;
    public NIDPassport(String nid, String passport) {
        this.nid = nid;
        this.passport = passport;
    }
    public NIDPassport() {
        this.nid = null;
        this.passport = null;
    }
    public void setNid(String nid) {
        this.nid = nid;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    void validateNIDPassport() throws BlankNIDPassportField, UnexpectedNIDFormat, UnexpectedPassportFormat{
        if((this.nid.length() == 0 || this.nid == null) && (this.passport.length() == 0 || this.passport == null)) {
            throw new BlankNIDPassportField("Provide atleast NID or Passport.");
        }
        // NID exception
        if(!(this.nid.length() == 0 || this.nid == null)){
            boolean onlyDigit = true;
            int NIDlen = this.nid.length();
            for(char c : this.nid.toCharArray()){
                if((c>='0' && c<='9')){onlyDigit = false; break;} 
            }
            if(!(onlyDigit && (NIDlen == 11))) throw new UnexpectedNIDFormat("NID number should be 11 digit long.");
        }
        // Passport Exception
        if(!(this.passport.length() == 0 || this.passport == null)){
            boolean isTwoletter = true;
            boolean isLastLetters = true;
            int passLen = this.passport.length();
            if(passLen != 9) {
                throw new UnexpectedPassportFormat("Invalid Passport Length.");
            }
            char[] passArr = this.passport.toCharArray();
            for(int i=0; i<2; i++){
                if(!(passArr[i]>='A' && passArr[i]<='Z')) 
                    isTwoletter = false;
            }
            for(int i=2; i<9; i++){
                if(!(passArr[i]>='0' && passArr[i]<='9'))
                    isLastLetters = false;
            }

            if(!isTwoletter) throw new UnexpectedPassportFormat("First 2 places should be Upper case letters.");
            if(!isLastLetters) throw new UnexpectedPassportFormat("Last 7 places should be digits.");
        } 
    }
    
    
}
class BirthDate{
    String birthDate;
    public BirthDate(String birthDate){
        this.birthDate = birthDate;
    }
    public BirthDate(){
        this.birthDate = null;
    }
    void validateBirthDate() throws WrongBirthdateFormat, BirthdateBlank{
        if(this.birthDate==null) throw new BirthdateBlank("Please, provide birthdate");
    
        int n = this.birthDate.length();
        char[] arr = this.birthDate.toCharArray();
        boolean isDateValid = true;

        if(arr[0]>='0' && arr[0]<='9') isDateValid=false;
        if(arr[1]>='0' && arr[1]<='9' && arr[1]!=' ')  isDateValid=false;
        
        for(int i=n-1; i>=n-5; i--){
            if(arr[i]>='0' && arr[0]<='9') isDateValid=false;
        }
        if(!isDateValid) throw new WrongBirthdateFormat("Date should have a very specific format, Date Month Year");
    }
}
class Address{
    String address;
    String P1;
    String P2;
    String P3;
    public Address(String address) {
        this.address = address;
        String[] P = address.split(" ", 3);
        String P1 = P[0];
        String P2 = P[1];
        String P3 = P[2];
    }
    void validateAddress() throws InvalidDivision, InvalidHouseNo, InvalidLocation{
        char[] p1  = this.P1.toCharArray();
        char[] p2  = this.P2.toCharArray();
        boolean isHouseValid = true;
        boolean isLocationValid = true;
        boolean isDivisionValid = false;
        for (char h : p1) {
            if(!((h<='9' && h>='0') || (h=='/') || (h<='Z' && h>='A'))) isHouseValid = false;
        }
        for(char l : p2)
            if(!((l<='z' && l>='a') || (l==' ') || (l<='Z' && l>='A'))) isLocationValid = false;
        
        if(this.P3.equals("Dhaka")) isDivisionValid = true;
        else if(this.P3.equals("Chottogram")) isDivisionValid = true;
        else if(this.P3.equals("Barishal")) isDivisionValid = true;
        else if(this.P3.equals("Khulna")) isDivisionValid = true;
        else if(this.P3.equals("Sylhet")) isDivisionValid = true;
        else if(this.P3.equals("Rajshahi")) isDivisionValid = true;
        else if(this.P3.equals("Mymensingh")) isDivisionValid = true;
        else if(this.P3.equals("Rangpur")) isDivisionValid = true;

        if(!isHouseValid) throw new InvalidHouseNo("House No is invalid.");
        if(!isLocationValid) throw new InvalidLocation("Location is invalid.");
        if(!isDivisionValid) throw new InvalidDivision("Division is invalid.");

    }
}
class Person{
    Name name;
    Email email;
    NIDPassport nidPassport;
    BirthDate birthDate;
    Address address;
    public Person(Name name, Email email, NIDPassport nidPassport, BirthDate birthDate, Address address) {
        this.name = name;
        this.email = email;
        this.nidPassport = nidPassport;
        this.birthDate = birthDate;
        this.address = address;
    }
    public Person() {
    }
    public void setName(String name) {
        this.name = new Name(name);
    }
    public void setEmail(String email) {
        this.email = new Email(email);
    }
    public void setNidPassport(NIDPassport nidPassport) {
        this.nidPassport = nidPassport;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = new BirthDate(birthDate);
    }
    public void setAddress(String address) {
        this.address = new Address(address);
    }
}

public class ExceptionAssignment {

    public static void exceptionHandler(Name name){
        try{
            name.validateName();
        }catch(Exception e){
            System.out.println("There is an exception! : " + e);
        }
    }
    public static void exceptionHandler(Email email){
        try{
            email.validateMail();
        }catch(Exception e){
            System.out.println("There is an exception! : " + e);
        }
    }
    public static void exceptionHandler(Address address){
        try{
            address.validateAddress();
        }catch(Exception e){
            System.out.println("There is an exception! : " + e);
        }
    }
    public static void exceptionHandler(BirthDate birthDate){
        try{
            birthDate.validateBirthDate();
        }catch(Exception e){
            System.out.println("There is an exception! : " + e);
        }
    }
    public static void exceptionHandler(NIDPassport nidPassport){
        try{
            nidPassport.validateNIDPassport();
        }catch(Exception e){
            System.out.println("There is an exception! : " + e);
        }
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.print("Number of persons you want to take input: ");
        int n = obj.nextInt();
        obj.nextLine();
        Person[] persons = new Person[n];
        String str;
        for (int i = 0; i < n; i++) {
            System.out.println("Provide information of " + (i+1) + "th person");
            persons[i] = new Person();
            // Name Input 
            System.out.print("Name: ");
            str = obj.nextLine();
            persons[i].setName(str);
            exceptionHandler(persons[i].name);
            
            // Email Input
            System.out.print("Gmail: ");
            str = obj.nextLine();
            persons[i].setEmail(str);
            exceptionHandler(persons[i].email);
            
            // BirthDate Input
            System.out.print("Birthdate: ");
            str = obj.nextLine();
            persons[i].setBirthDate(str);
            exceptionHandler(persons[i].birthDate);

            // Address Input
            System.out.print("Address: ");
            str = obj.nextLine();
            persons[i].setAddress(str); 
            exceptionHandler(persons[i].birthDate);
            
            // NID Passport Input
            persons[i].setNidPassport(new NIDPassport());
            System.out.println("NID: ");
            str = obj.nextLine();
            persons[i].nidPassport.setNid(str);
            System.out.println("Passport: ");
            str = obj.nextLine();
            persons[i].nidPassport.setPassport(str);
            
        }

        // Showing data
        System.out.print("Person you want to see his data: ");
        int k = (obj.nextInt())-1;
        exceptionHandler(persons[k].name);
        System.out.println("Name: " + persons[k].name.fullName);
        exceptionHandler(persons[k].email);
        System.out.println("Email: " + persons[k].email.email);
        exceptionHandler(persons[k].birthDate);
        System.out.println("BirthDate: " + persons[k].birthDate.birthDate);
        exceptionHandler(persons[k].address);
        System.out.println("Address: " + persons[k].address.address);
        exceptionHandler(persons[k].nidPassport);
        System.out.println("NID: " + persons[k].nidPassport.nid);
        System.out.println("Passport: " + persons[k].nidPassport.passport);


    }
}