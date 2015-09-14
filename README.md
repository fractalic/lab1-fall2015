Machine Problem 0: Working with a Tour de France DataSet
===
... could be a tour de force.


* This is the 0th machine problem and is worth 3% of the course grade. If you do not get all the 3% for this MP but you have completed Lab 1 in time then you will get up to 0.5% because of Lab 1.
* **This is an individual assignment.** 
* **It is due September 17 (Wednesday) by 11:00 a.m.** You cannot use any late days for this machine problem.
* **How should you submit your work?** Please create a private repository at BitBucket called `mp0`. Share this repository (provide write access) to the TAs and the instructors (BitBucket ids will be provided on Piazza). Commit and push all your code to this repository before the deadline. You should always commit and push intermediate stages as well, so that you have saved your work safely.

For this machine problem, you will have to write _a small amount of code_ but you should spend time reading the provided code to understand what you should write (which should, hopefully, be relatively straightforward) and also to understand how certain operations can be implemented in Java. Reading and learning from examples is one of the fastest ways to understand a new programming language. And do not hesitate in making changes and understanding what the impact of those changes is.

In this machine problem, you will work with a real dataset that represents that contains information about the average speeds of different bicyclists (bikers?) in the Tour de France. The data was gathered from the 2005 to 2012 editions of the Tour de France. All this data is stored in a simple text file (`tdf.txt`) in a comma-separated-values (CSV) format. If you look at the dataset, you will see four columns in each line. Each column is separated by a comma. The first column represents the competition year. The second column represents the average speed for a bicyclist, and the third and fourth columns represent the last name and first name, respectively, of the bicyclists.

We want to compute:
* The improvement from one edition of the Tour de France to the next for each bicyclist and then display that best improvement for each bicyclist. As an example, suppose Lance Armstrong improved his speed by 0.5 km/hr from 2005 to 2006, and then by 0.75 km/hr from 2006 to 2007, then his best improvement from these two data points is 0.75 km/hr. (I did make this example up because Lance Armstrong’s last Tour de France was in 2005. And no one really wants to talk about Lance…)
* The median of all average speeds at the Tour de France between 2005 and 2012.
* The median of medians: Compute the median speed for each bicyclist and then compute the median from among these medians.

The provided code should compile and run, except that it does not compute any of the required data. That is your task.

What the provided code does do is to:
* Read data from the dataset;
* Create an object per bicyclist (the associated class is `Biker`) where the bicyclist’s name and average speeds in the editions of the Tour de France they participated in are stored;
* Invoke the incomplete method to get the best improvement for each bicyclist and display this data (initially all 0s).

What does the `Biker` class look like (see `Biker.java`)? It contains `String`s for storing the bicyclist’s name and then it contains a `Map` that represents the pair `<year, average speed>`. For each bicyclist, given a year of participation, we can retrieve the bicyclist’s average speed in that year. A map is a data type that helps facilitate this form of storage.

The list of all bicyclists (see the variable `allBikers` in `TdFMain.java`) is also a map. This map helps us retrieve a bicyclist’s `Biker` object give the bicyclist’s full name. This map essentially stores the pair `<full name, Biker object>`. We can use this map to iterate over all the bicyclists that we have data for.

Read the provided source code carefully. You are explicitly told where you should write your own code. Do not change the program structure or the data types. You will be writing simple procedural code and you can learn from looking at the given implementation of other methods.

To better understand the Java features that are used, you may want to read/view the following:
* Slides on the [Java Collections Framework](https://dl.dropboxusercontent.com/u/567187/EECE%20210/Java/JavaCollectionsFramework.pdf)
* A video on [`Map`s](http://media.pearsoncmg.com/aw/aw_reges_bjp_2/videoPlayer.php?id=c11-3)
* Slides on [File Processing](https://dl.dropboxusercontent.com/u/567187/EECE%20210/Java/FileProcessing.pdf)
* A video on [line-based processing](http://media.pearsoncmg.com/aw/aw_reges_bjp_2/videoPlayer.php?id=c6-2)
* Some of the Java documentation on I/O: [JavaInputStreamReader](http://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html), [BufferedReader](http://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html).
* Java documentation on [String](http://docs.oracle.com/javase/6/docs/api/java/lang/String.html)s is extremely useful and can save you a lot of time.

Think about what an interface is. You will likely encounter this term in the readings/videos. We will discuss interfaces in more detail later.

===

**Advice**
* Start early!
* Make sure you are familiar with the [course policies](http://eece210.ece.ubc.ca/policies/), in particular on academic integrity and collaboration.
* It is inefficient to expect to learn many language features before writing code. Dive right in! Eclipse can be your friend, in particular use the code completion feature to see what options you have.
* Use the Java documentation on the Sun/Oracle website. Learn to read it.
* Ask for help early — but not too early!
* Do not post your own code on Piazza.
* _Repeat: Start early!_
