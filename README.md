# Keyboard Teacher

Program teaches user typing on a keyboard by retyping in the phrases from the input file line by line. It checks for mistakes and outputs information about them.

## Usage example

### Input file

```
This is the first line.
This is the second line.
This is the last line.
```

### User input

After reading an input file, the first line appears and user must type it in correctly:

```
This is the first line.
This is the first line.
```
Then, the second line appears. But let's say that user made a mistake while typing in the phrase:

```
This is the second line.
This us the second line.
```

The program returns the information about a mistake:

```
Mistake on position 6. You typed in 'u' char instead of 'i' char. Try again!
```

Then it once again displays the same line. But let's say that user makes another mistake - this time the phrase he types in is too short:

```
This is the second line.
This is the second lin
```

The program once again returns the information about a mistake:

```
Mistake on position 23. Given line is too short. You should type in 'e' char now. Try again!
```

Again, there appears the same line and user finally types it in correctly:

```
This is the second line.
This is the second line.
```

Then, the program displays another, last line. User types it in correctly, but he also adds another char in the end:

```
This is the last line.
This is the last line..
```

The program returns information about a mistake:

```
Mistake on position 23. Given line is too long. Try again!
```

Finally, the user types in the phrase correctly:

```
This is the last line.
This is the last line.
```

### End of the program

The program finishes:

```
Congratulations!
```
