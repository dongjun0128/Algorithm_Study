public class test1 {
    public static void main(String[] args) {
        User user = new User("dongjun");

        foo(user);

        System.out.println(user);
    }

    public static void foo(User b) {
        //b = new User("asd");
        b.name = "asd";
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
