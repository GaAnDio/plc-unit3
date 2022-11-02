import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.EnumSet;

public class OSTypeEnum
{
    enum OS { ANDROID, IOS, MACOSX, WINDOWS8, WP8, VXWORKS }
    
    enum OSType { DESKTOP, EMBEDDED, MOBILE }
    
    private static <E extends Enum<E>> E getEnumElement(String elementTypeName, Class<E> elementType)
    {
        boolean haveResult = false;
        E result = null;
        Scanner stdin = new Scanner(System.in);
        
        while ( ! haveResult )
        {
            System.out.print("Input " + elementTypeName + ": ");
            try
            {
                result = Enum.valueOf(elementType, stdin.next().toUpperCase());
                haveResult = true;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Not a valid " + elementTypeName + ".");
                stdin.nextLine(); // skip the invalid input
            }
        }
        
        return result;
    }
  
    private static OS OSType2os (OSType osType)
    {
        OS type = null;
        
        switch (osType)
        {
        case MOBILE:
            type = OS.IOS;
            break;
        case DESKTOP:
            type = OS.WINDOWS8;
            break;
        case EMBEDDED:
            type = OS.VXWORKS;
            break;
        }
        
        return type;
    }

    public static void main(String[] args)
    {
        System.out.print("Known OSs = ");
        for (OSType t : EnumSet.allOf(OSType.class)) 
        {
            System.out.print(t + " ");
        }
        System.out.println();
        
        OSType osType = getEnumElement("operating system type", OSType.class);
        System.out.println(osType + " type has a OS of: " + OSType2os(osType));
    }
}