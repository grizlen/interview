package work1.builder;

public class Person {

    public static Builder builder(String firstName, int age) {
        return new Builder(firstName, age);
    }

    private String
            firstName,
            lastName,
            middleName,
            country,
            address,
            phone,
            gender;
    int age;

    private Person() {}

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder {
        private final Person person;

        private Builder(String firstName, int age) {
            person = new Person();
            person.firstName = firstName;
            person.age = age;
        }

        public Person build() {
            return person;
        }

        public Builder lastName(String lastName) {
            person.lastName = lastName;
            return this;
        }
        public Builder middleName(String middleName) {
            person.middleName = middleName;
            return this;
        }
        public Builder country(String country) {
            person.country = country;
            return this;
        }
        public Builder address(String address) {
            person.address = address;
            return this;
        }
        public Builder phone(String phone) {
            person.phone = phone;
            return this;
        }
        public Builder gender(String gender) {
            person.gender = gender;
            return this;
        }
    }
}
