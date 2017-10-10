package interfaces.impls;

import interfaces.AddressBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Person;

public class CollectionAddressBook implements AddressBook {
    private ObservableList<Person> personObservableList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personObservableList.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {
        personObservableList.remove(person);
    }

    public ObservableList<Person> getPersonObservableList() {
        return personObservableList;
    }

    public void fillTestData(){
        personObservableList.add(new Person("Иван", "23948723948"));
        personObservableList.add(new Person("Роман", "345345345"));
        personObservableList.add(new Person("Антон", "345345345"));
        personObservableList.add(new Person("Джон", "23423423"));
        personObservableList.add(new Person("Джек", "234234"));
        personObservableList.add(new Person("Алиса", "456456"));
        personObservableList.add(new Person("Боб", "34534345"));
    }
}
