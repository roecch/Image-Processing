### Image Manipulation Model

_See Image Processing.png for class diagram in the `res` folder._


## Controller:
##### `ImageController`

- `public void commandByFile(String filename)` reads a valid script and executes the given commands


- `public void commandByUser()` runs and requires user input from the console 


## Model:

#### Images:
##### `IImage`

#### Images:
##### `IImage`

- `IPixel[][] getPixels()` retrieves the pixels of an image in 2D-array form

- `String export()` renders the image in ASCII PPM format 

The `IImage` interface is the interface for the model of our application.

The `IImage` interface is implemented by the abstract class `AImage`. The purpose of this 
abstract class is to reduce redundant code for functionality shared between implementing classes of
`IImage`, particularly for the method `public String export()`. 

We provided 3 class representations of images that both extend from 
`AImage`: `PPMImage` and `SelfImage`. 

`PPMImage` is a representation of an image derived from a given PPM file. The PPM file 
must be in ASCII form. The field `IPixel[][] pixels` represents and stores the individual pixels
of the image. Initialize a class instance through the constructor `PPMImage(String fileName)`, 
where the fileName is the path of the PPM file in this project's directory.

`SelfImage` is an image of a checkerboard with the colors of the tiles randomly assigned based on 
a given array of `Color`s. Dimensions of the board are also specified by the user. Pixel objects
are stored in a 2D-array field. Initialize a class instance through the constructor 
`SelfImage(int numTile, int size, Color[] colors)`, where the number of tiles must be a square 
number.

`MultiLayerImage` is a representation of a multi-layer image model. Images are stored in the field
`List<IImage> layers`. Layers marked as invisible have their indices stored in the field 
`List<Integer> invisLayers`. The layer that the user is currently working with is stored in the 
`int current` field. In addition to all inherited methods, the class has the method 
`void exportAll(String prefix, ExportType type)`, which exports all visible layers in the image, 
along with other methods to change the current layer, instantiate blank layers (layers without an 
image loaded to them), and set layers to be visible/invisible.

#### Pixels:


##### `IPixel`

- `public int getRValue()` retrieves the value of the red channel of this Pixel's color
- `public int getGValue()` retrieves the value of the green channel of this Pixel's color
- `public int getBValue()` retrieves the value of the blue channel of this Pixel's color
- `public IPixel applyKernelFactor(double factor)` applies a given kernel factor to this pixel
- `public IPixel applyLinearTransformation(double[][] matrix)` applies a linear color 
transformation to this pixel


We created one implementation of this interface, `PixelImpl`, which represents a pixel with
its color represented by 3 `int` fields, each one representing an RGB channel value.


#### Image Effects/Manipulations:

##### `IManipulation`

- `public void apply(IImage image)` applies this IManipulation object to the given image
- `public void clamp(IImage image)` clamps the values of an image's pixels after an effect has been 
applied

The abstract class `AManipulation` implements `IManipulation` and
reduces duplication of code among `IManipulation` implementations for the 
`clamp()` method.


The abstract class `AFilter` extends `AManipulation` and also serves to reduce duplicate code among
class implementations of filters. 


The classes `BlurFilter` and `SharpenFilter` both extend `AFilter`. `BlurFilter` blurs the image by
applying a kernel to every pixel. `SharpenFilter` sharpens an image through applying a kernel to 
every pixel.


The abstract class `AColorTransform` extends `AManipulation` and serves to reduce duplicate
code among class implementations of linear color transformations.

The classes `SepiaTrans` and `GreyscaleTrans` both extend `AColorTransform`. Each class has a 
`static` field representing its matrix, which is then passed through the 
inherited `apply(IImage image)` method to execute the color transformation.



##### `ImageUtil`
The `ImageUtil` class contains methods that can be applied to any `IImage` implementation, including
methods that handle the I/O required for exporting and importing images into PPM, JPG, and PNG
formats from a given file path. The `main` method demonstrates how we applied various image effects
and exported them in PPM file format.

#### Images:

###### Citations

- `bad.ppm` was taken and edited by Alex.
- `testing.ppm` was taken and edited by Alex.
- `blue.png` was taken and edited by Monroe.
- `blue.jpg` was taken and edited by Alex.  
- `originalFlowers.ppm` posted by wal_172619 on pixabay.com
(https://pixabay.com/photos/woman-grace-double-exposure-lilac-6274584/). For free commercial use.

- `originalLandscape.ppm` posted by enriquelopezgarre on pixabay.com
(https://pixabay.com/photos/landscape-castle-fantasy-sea-sky-4956790/). For free commercial use.


We authorize use of all original works for this assignment.

