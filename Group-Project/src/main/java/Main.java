public class Main {
    public static void main(String[] args){
        /* Object that stores the JSON data in corresponding Product and StoreInfo objects with a method to parse file.
        *
        *This object is in the main where the other frame classes get called so that it can be passed to both objects.
        * They will share the same reference to jsonToObject, since that object stores the JSON information as objects */
        JsonToObject jsonToObject = new JsonToObject();

        // jsonToObject is passed through the constructor, so that both frame classes can access the data of the object
        Frame2 frame2 = new Frame2(jsonToObject);
        Frame1 frame1 = new Frame1(jsonToObject,frame2);


    }
}