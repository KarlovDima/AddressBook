package interfaces.impls;

import interfaces.AddressBook;
import objects.Person;

import java.util.ArrayList;

public class CollectionAddressBook implements AddressBook {
    private ArrayList<Person> personArrayList = new ArrayList<>();

    @Override
    public void add(Person person) {
        personArrayList.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {
        personArrayList.remove(person);
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public void fillTestData(){
        personArrayList.add(new Person("Иван", "23948723948"));
        personArrayList.add(new Person("Роман", "345345345"));
        personArrayList.add(new Person("Антон", "345345345"));
        personArrayList.add(new Person("Джон", "23423423"));
        personArrayList.add(new Person("Джек", "234234"));
        personArrayList.add(new Person("Алиса", "456456"));
        personArrayList.add(new Person("Боб", "34534345"));
    }
}
