package Exceptions;

public enum Error
{
    FILE_NAME_ERROR("\nFile doesn't exist or you made a typo, dummie"),
    MISSING_AUTO_PRICE("\nYou are missing auto's base price in txt file!"),
    NEGATIVE_AUTO_PRICE("\nNegative Auto price in the reading file!"),
    MISSING_OPTION_SET_NAME("\nYou are missing OptionSetName!"),
    MISSING_OPTION_NAME("\nYou are missing OptionName!"),
    MISSING_OPTION_NAME_LINE("\nMissing name line in Option!"),

    UNKNOWN_ERROR("\nThis exception is not listed in custom exception list");

    public final String message;

    private Error(String message)
    {
        this.message = message;
    }
}
