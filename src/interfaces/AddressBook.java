package interfaces;

import objects.Person;

public interface AddressBook {
    void add(Person person);

    void update(Person person);

    void delete(Person person);
}
