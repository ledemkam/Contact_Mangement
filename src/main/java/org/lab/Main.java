package org.lab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = buildContactList();
        //add a new contact
        Contact contact1 = new Contact("Drücker", "sam", "drück@mail.de", 224067890);
        contacts.add(contact1);
        //edit a contact
        editContact(contacts, "sam",  "drück@mail.de", 20000000);
        //delete a contact
        deleteContact(contacts, "molil@mail.com");
       //sort all contact alphabetically
        sortByName(contacts);


        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        //search a contact by email
        try{
            Contact searchContact = searchContact(contacts, "molil@mail.com", 894567890);
        }
        catch (ContactNotFoundException e){
            System.err.println(e.getMessage());
        }

        //get all email
        List<String> emails = getAllEmails(contacts);
        for (String email : emails) {
            System.out.println(email);
        }


    }

        static void sortByName(List<Contact> contacts) {
            Collections.sort(contacts,new Comparator<Contact>(){

                @Override
                public int compare(Contact o1, Contact o2){
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }

       static List<Contact> buildContactList() {
        // Create a new Contact object
        List<Contact> contact = new ArrayList<>();
        contact.add(new Contact("Doe", "John", "ledem@mail.fr", 1234567890));
        contact.add(new Contact("Doe", "Jane", "jane@oo.de", 276543210));
        contact.add(new Contact("kells", "Lil", "molil@mail.com", 894567890));
        contact.add(new Contact("Amozov", "Joshua", "josh@mail.de", 304567890));
        contact.add(new Contact("alor", "koi", "lock@mail.com", 994567890));
        return contact;
       }


       //edit a contact
       static void editContact(List<Contact> contacts,String vorname, String email, long newTelefon){
           for (Contact contact : contacts) {
               if (contact.getEmail().equals(email)) {
                   contact.setTelefon(newTelefon);
               }
           }
       }

       //delete a contact
         static void deleteContact(List<Contact> contacts, String email){
        //enfin d eviter les bugs et les exceptions de concurrence lorsqu on veut supprimmer une liste
             // il est preferable d utiliser les Iterator
             Iterator<Contact> iterator =  contacts.iterator();
             while (iterator.hasNext()){
               Contact contact = iterator.next();
               if (contact.getEmail().equals(email)){
                   iterator.remove();
               }
             }
         }

         //search a contact by email or name

        static Contact searchContact(List<Contact> contacts, String email, long telefon) {
            for (Contact contact : contacts) {
                if (contact.getEmail().equals(email) || contact.getTelefon() == telefon) {
                    return contact;
                }
            }
            throw new ContactNotFoundException("Contact not found ");
        }

        //get all email
        static List<String> getAllEmails(List<Contact> contacts){
            List<String> emails = new ArrayList<>();
            for (Contact contact : contacts) {
                emails.add(contact.getEmail());
            }
            return emails;
        }


}