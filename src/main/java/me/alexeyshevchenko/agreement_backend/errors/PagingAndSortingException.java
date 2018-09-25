package me.alexeyshevchenko.agreement_backend.errors;

public class PagingAndSortingException extends Exception {
    public PagingAndSortingException(String userLogin) {
            super("User `" + userLogin + "` doesn't exist");
        }

     public PagingAndSortingException (long userId){
            super("Error. Introduced number `" + userId + "` incorrect." +
                    " Number of list can`t be less then 0." +
                    "Number of size list can`t be less then 15 ");
        }
    }

