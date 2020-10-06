import java.beans.*;
import java.io.*;
import java.util.*;

//casual, buisness and catering customers extend this class 
public abstract class Customer {
    public abstract List<Menu> getOrder(); //each customer has their own order
}