_See our main method in the `Main` class in the `cs3500.image` package._

##Commands:

- `create layer` creates an empty layer in the `MultiLayerImage` model.
    * Please read Note 1
  * The number given must be one more than the current number of layers.
  


- `current` changes the layer the user is currently on to the layer of the given number
    * Please read Note 1
  * The number given must be at least 1 and less than or equal to the current amount of layers
  

- `load` load the file of the given name to the current layer
  * Please read Note 3
  

- `save` exports the topmost visible layer in the image (if there is one) to its pre-existing
  upload file location
  

- `delete` removes the layer of the given number from the multilayered image
  * Please read Note 1
  

- `invisible` makes the layer of the current index invisible
  * Please read Note 1
  

- `visible` makes the layer of the current index visible
  * Please read Note 1
  

- `duplicate` duplicates the layer of the current index
  * Anything in the line after "duplicate " will be ignored.
  

- `export all` exports all the layers as their original name and creates a text file titled the 
  given string with all the layers in the MultiLayered Image. These files will all go to the `res`
  folder.
  * Please read Note 3
  

- `#` comments out following text on same line, will be ignored
  

- `blur` blurs the current layer
  * Please read Note 2
  

- `sharpen` sharpens the current layer
  * Please read Note 2
  

- `sepia` makes the current layer red and brown toned
  * Please read Note 2
  

- `greyscale` makes the current layer black and white
  * Please read Note 2
  
## Notes
- Note 1
  * This command must be followed with the written form of numbers
    and cannot be followed by anything else unless commented out.
    (ex. 44 should be forty four or forty-four and not 44 or forty fourth)
- Note 2
  * Command must be written alone. (ex. "blur" cannot be called with "blur two")

- Note 3
  * When using this command, after the wanted command is written, make sure to write string in
    format of file name.
    (ex. "Export all testMyProgram" is allowed but "Export all test My Program" is not)
- The controller is lenient with multiple whitespaces between words.
- The controller is case-insensitive.
