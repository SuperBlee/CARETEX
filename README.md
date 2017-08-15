# Temporal Expression Extraction for Case Report

### Introduction

This repo provide a annotation tool for temporal expressions using [SUTime](https://nlp.stanford.edu/software/sutime.shtml). Please look at the `annotate.java` in src for clear instruction.

### Dependencies

Sorry. I am a bad guy. I promise next time I will maven to maintain the project. But this time, please add 

1. `commons-io.2.5`
2. `stanford-corenlp-3.8.0`
3. `org.json.simple`

to your external library. Thanks.

### Run

Here we introduce how to run the code. Please look at the`main` of  `annotate.java`. Write the name of the documents you want to annotate in `sample_data`  `filelist.txt`, and put all the data in `sample_data` folder.

Assign your rules in `src/rules`, if you wish to extend or modify, please use `TempExpExtract(rule1, rule2, rule3)`.



thanks.

Zeyu Li

zyli@cs.ucla.edu

