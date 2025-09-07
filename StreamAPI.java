package com.streamsconcept;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI 
{
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException 
	{
		
		//*************** Stream Imports starts***********************//
		// 1. From Array
		String[] namesArray = { "Jeff", "Bill", "Mark" };
		Stream<String> streamFromArray = Stream.of(namesArray);
		streamFromArray.forEach(System.out::println);

		// 2. From ArrayList
		List<Integer> numbersList = Arrays.asList(10, 20, 30, 40, 50);
		Stream<Integer> streamFromList = numbersList.stream();
		streamFromList.forEach(System.out::println);

		// 3. From individual objects
		Stream<String> streamOfObjects = Stream.of("One", "Two", "Three");
		streamOfObjects.forEach(System.out::println);

		// 4. Using Stream.builder()
		Stream.Builder<Double> streamBuilder = Stream.builder();
		streamBuilder.accept(1.1);
		streamBuilder.accept(2.2);
		streamBuilder.accept(3.3);
		Stream<Double> builtStream = streamBuilder.build();
		builtStream.forEach(System.out::println);
		
		//***************Stream Imports Ends***********************//
		
		System.out.println();
		
		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 10, 17);
		List<String> list1 = List.of("Aakash", "Sri", "Rishi", "Vicky", "arun");
		List<Integer> list = List.of(1, 9, 5, 7, 344, 44, 232);
		
		//ForEach - forEach() is the simplest and most common operation; it loops over the stream elements, calling the supplied function on each element.
		System.out.println("ForEach");
		numbers.stream().forEach(a -> System.out.print(a+" "));
		
		/*
		 * Map - map() is used to transform each element of a stream. It applies a
		 * function to every element and produces a new stream. The new stream can be of
		 * the same type or a different type
		 */
		System.out.println();
		System.out.println();
		System.out.println("Map");
		List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

		System.out.println(squares); 
		
		// Convert to uppercase
		list1.stream().map(a -> a.toUpperCase()).forEach(s -> System.out.print(s + " "));
		System.out.println();

		// Capitalise the String
		list1.stream().map(a -> a.substring(0, 1).toUpperCase().concat(a.substring(1)))
				.forEach(a -> System.out.print(a + " "));
		System.out.println();
		
		/*
		 * Streams are often intermediate pipelines. To actually get the result out of a
		 * stream, we use a terminal operation. collect() is the most common terminal
		 * operation. It is used to gather the results into a List, Set, Map, or even a
		 * String.
		 */
		System.out.println();
		System.out.println("Collect");
		// List
		List<Integer> collectedNames = numbers.stream()
                .collect(Collectors.toList());
		System.out.println(collectedNames); 
		
		// Set
		Set<Integer> uniquenum = numbers.stream()
                .collect(Collectors.toSet());
		System.out.println(uniquenum); 
		
		/*
		 * filter() is used to select only elements that match a condition (predicate).
		 * It produces a new stream with only the matching elements.
		 */
		System.out.println();
		System.out.println("Filters");
		List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
		System.out.println(evens); 
		
		// filter a list Startwith "A"
		List<String> result = list1.stream().filter(s -> s.toUpperCase().startsWith("A")).toList();
		System.out.println(result);
		
		// Filter even numbers
		Predicate<Integer> divideByZero = a -> (a % 2 == 0);
		list.stream().filter(divideByZero).forEach(a -> System.out.print(a + " "));
		System.out.println();
		
		// Filter a list which is greater than 10
		list.stream().filter(a -> a > 10).forEach(a -> System.out.print(a + " "));
		System.out.println();

		// Filter and print the number divide by 3
		list.stream().filter(a -> a % 3 == 0).forEach(a -> System.out.print(a + " "));
		System.out.println();
		
		/*
		 * findFirst() returns the first element in the stream, wrapped in an Optional.
		 * If the stream is empty (no elements), the Optional will also be empty. We
		 * often combine it with .orElse(defaultValue) to avoid NullPointerException(returns Optional)
		 */
		System.out.println();
		System.out.println("FindFirst");
		Integer first = numbers.stream()
				                .filter(n -> n > 15)
				                .findFirst()
				                .orElse(null);
		System.out.println(first);
		
		/*
		 * Streams can be collected into Lists, Sets, etc. using collect(). But if you
		 * specifically want an array, you use toArray().
		 */
		System.out.println();
		System.out.println("ToArray");
		String[] namesArray1 = list1.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(namesArray1)); 
		
		/*
		 * flatMap() is used when the stream contains nested collections (like List of
		 * Lists). It “flattens” multiple inner streams into a single stream. Very
		 * useful for handling nested structures.
		 */
		System.out.println();
		System.out.println("FlatMap");
		List<List<String>> nestedNames = Arrays.asList(Arrays.asList("Jeff", "Bezos"), Arrays.asList("Bill", "Gates") ,Arrays.asList("Mark", "Zuckerberg"));
		// flatten into single stream of strings
		List<String> flatNames = nestedNames.stream()
		                                    .flatMap(Collection::stream)
		                                    .collect(Collectors.toList());
		System.out.println(flatNames);

		/*
		 * peek() is an intermediate operation (like map, filter). 
		 * It allows you to inspect or perform side-effects (like logging, debugging, or modifying values) while the stream is being processed. 
		 * Unlike forEach() (which is terminal), peek() lets you continue the stream pipeline. 
		 * Mostly used for debugging or applying small side effects, not for main logic.
		 */
		System.out.println();
		System.out.println("Peek");
		List<Integer> results = numbers.stream()
	            .peek(n -> System.out.println("Before doubling: " + n)) // debug
	            .map(n -> n * 2) // double the number
	            .peek(n -> System.out.println("After doubling: " + n)) // debug
	            .collect(Collectors.toList());

	    System.out.println("Final Result: " + results);
	    
		/* 
		 * Flattens nested structures into an IntStream 
		 */
	    System.out.println();
		System.out.println("flatMapToInt");
	    List<List<Integer>> numbersNested = Arrays.asList(Arrays.asList(1, 2, 3),Arrays.asList(4, 5),Arrays.asList(6, 7, 8, 9));
        // Flatten into IntStream
        IntStream intStream = numbersNested.stream()
                                           .flatMapToInt(lis -> lis.stream().mapToInt(Integer::intValue));
        // Now we can use IntStream methods
        System.out.println("Sum = " + intStream.sum()); // 45
		
        //****************************** 4 Core Concepts of Streams**************************//
        
        System.out.println();
		System.out.println("4 Core Concepts of Streams");
        
		/*
		 * 1. Method Types: Intermediate vs Terminal 
		 * Intermediate operations → return a new Stream, can be chained (filter, map, sorted, peek …). 
		 * Terminal operations → consume the stream and produce a result (forEach, collect, count, findFirst…). 
		 * After this, the stream cannot be reused.
		 */
		System.out.println();
		System.out.println("Intermediate vs Terminal");
        List<Integer> number = Arrays.asList(10, 20, 30, 40, 50);
        
	     // source: numbers
	     // intermediate: filter()
	     // terminal: count()
	     long count = number.stream()
	         .filter(n -> n > 25)   // intermediate
	         .count();              // terminal
	
	     System.out.println(count); // 3
	     
	     // Intermediate operations are lazy, they only build a pipeline. The actual execution happens when a terminal operation is invoked.
	     
	     
		/*
		 * 2. Stream Pipeline 
		 * A pipeline = Source → Zero or more intermediate operations → Terminal operation.
		 */
	     System.out.println();
	     System.out.println("Stream Pipeline");
	     List<String> names = Arrays.asList("Jeff", "Bill", "Mark", "Mike");

		  // Pipeline: source → filter → map → collect
		  List<String> resultd = names.stream()
		      .filter(name -> name.startsWith("M"))  // intermediate
		      .map(String::toUpperCase)              // intermediate
		      .collect(Collectors.toList());         // terminal
	
		  System.out.println(resultd); // [MARK, MIKE]
		  
		/*
		 * 3. Short-Circuiting Operations Some operations allow early termination,
		 * especially useful with infinite or very large streams. Examples: limit(),
		 * skip(), findFirst(), anyMatch().
		 */
		  System.out.println();
		  System.out.println("Short-Circuiting Operations");
		  // Infinite stream of powers of 2
		  Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

		  // Use skip and limit to make it finite
		  List<Integer> collect = infiniteStream
		      .skip(3)    // skip first 3 elements → [2, 4, 8]
		      .limit(5)   // take next 5 elements
		      .collect(Collectors.toList());

		  System.out.println(collect); // [16, 32, 64, 128, 256]
		  
		  // Short-circuiting operations make infinite streams usable by restricting how many elements are actually processed.
		  
		/*
		 * 4. Lazy Evaluation
		 * Streams are lazy. Intermediate operations do not run immediately.
		 * They only execute when a terminal operation is triggered.
		 * And even then, processing happens element by element, not one operation at a time for the whole dataset.
		 */
		  System.out.println();
		  System.out.println("Lazy Evaluation");
		  List<Integer> numb = Arrays.asList(10, 20, 30, 40);

		  int firsts = numb.stream()
		      .map(n -> {
		          System.out.println("Mapping: " + n);
		          return n * 2;
		      })
		      .filter(n -> {
		          System.out.println("Filtering: " + n);
		          return n > 30;
		      })
		      .findFirst()
		      .orElse(-1);

		  System.out.println("Result = " + firsts);
		  
		/*
		 * Intermediate vs Terminal
		 * Intermediate = lazy, returns new stream (map, filter). 
		 * Terminal = consumes stream, produces result (count, collect).
		 * 
		 * Pipeline = Source → Intermediates → Terminal.
		 * 
		 * Short-Circuiting = stop early (limit, skip, findFirst). Useful for infinite
		 * streams.
		 * 
		 * Lazy Evaluation = Operations are applied element by element, only as much as
		 * needed to produce the result.
		 */

		//****************************** 4 Core Concepts of Streams End**************************//

        
        //*********Sort in Streams*********//
	    System.out.println();
	    System.out.println("Sort in Streams");
		List<Integer> sortedNumbers = list.stream().sorted().collect(Collectors.toList());
		System.out.println(sortedNumbers);

		// Sorting in Desc order
		List<Integer> reverseList = list.stream().sorted(Comparator.reverseOrder()).toList();
		System.out.println(reverseList);
		
		// List of List
		List<List<Object>> list2 = Arrays.asList(Arrays.asList("Apple", 10000, 23), Arrays.asList("Car", 20000, 25), 
				Arrays.asList("Dog", 30000, 19), Arrays.asList("Aog", 10000, 19));

		// Sort using index 3
		List<List<Object>> sortedListIndex = list2.stream().sorted(Comparator.comparing(a -> (Integer) a.get(2)))
				.toList();
		System.out.println(sortedListIndex);

		// Sort using index 3 followed by index 2
		List<List<Object>> sortedListIndexDesc = list2.stream()
				.sorted(Comparator.comparing(a -> (Integer) ((List<Integer>) a).get(2)).reversed()
						.thenComparing(Comparator.comparing(z -> ((List<String>) z).get(0))))
				.toList();
		System.out.println(sortedListIndexDesc);

		List<List<Object>> list3 = Arrays.asList(Arrays.asList("30-01-2025", 750), Arrays.asList("31-01-2025", 250),
				Arrays.asList("29-01-2025", 150), Arrays.asList("30-01-2025", 250), Arrays.asList("29-01-2025", 250));

		// Grouping by date and summing the second column (index 1)
		Map<String, Integer> result1 = list3.stream().collect(Collectors.groupingBy(e -> (String) e.get(0), 
				// Group by date (index 0)
				Collectors.summingInt(e -> (Integer) e.get(1)) 
				// Sum the second value (index 1)
		));
		System.out.println(result1);
		// Alternate
		List<Map<String, Integer>> list4 = Arrays.asList(Map.of("30-01-2025", 750), Map.of("31-01-2025", 250),
				Map.of("29-01-2025", 150), Map.of("30-01-2025", 250), Map.of("29-01-2025", 250));

		Map<String, Integer> result2 = list4.stream().flatMap(map -> map.entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, // Key: Date
						Map.Entry::getValue, // Value: Amount
						Integer::sum // Merge function: Sum values if key exists
				));
		System.out.println(result2);
		
		
		// MIN and MAX
		System.out.println();
	    System.out.println("MIN and MAX");
		int min = numbers.stream()
			    .min(Integer::compareTo)
			    .orElse(-1);

			int max = numbers.stream()
			    .max(Integer::compareTo)
			    .orElse(-1);

		System.out.println("Min = " + min); 
		System.out.println("Max = " + max);
		
		String shortest = list1.stream()
			    .min(Comparator.comparing(String::length))
			    .orElse("None");
			System.out.println(shortest); 

			
		/*
		 * distinct 
		 * Removes duplicate elements using .equals() and .hashCode()
		 */
		System.out.println();
	    System.out.println("DISTINCT");
		List<Integer> distinctNums = numbers.stream()
			    .distinct()
			    .collect(Collectors.toList());

		System.out.println(distinctNums); 
		
		
		/*
		 * allMatch(), anyMatch(), noneMatch()
		 * These are short-circuiting operations.
		 * They stop processing as soon as the result is determined.
		 */
		System.out.println();
	    System.out.println("allMatch(), anyMatch(), noneMatch()");
		boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0); 
		boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0); 
		boolean noneDivBy3 = numbers.stream().noneMatch(n -> n % 3 == 0); 

		System.out.println(allEven);      
		System.out.println(anyEven);      
		System.out.println(noneDivBy3);
		
		/*
		 * Java Stream Specializations 
		 * 
		 * A normal Stream<T> works with object references.
		 * Example: Stream<Integer> (but integers here are boxed objects).
		 * 
		 * Specialized streams (IntStream, LongStream, DoubleStream) work directly with primitives (int, long, double). 
		 * 👉 Saves memory + improves performance by avoiding boxing/unboxing.
		 * 
		 * Specialized streams add extra numeric operations that are not available on Stream<T>.
		 * 
		 * 👉 IntStream	sum(), average(), range(), rangeClosed(), boxed()
		 * 👉 LongStream	sum(), average(), range(), rangeClosed(), boxed()
		 * 👉 DoubleStream	sum(), average(), boxed()
		 * 
		 * boxed() converts back to Stream<T>
		 */
		
		
		/*
		 * Reduce
		 * reduce(identity, accumulator) → always returns a value.
		 * reduce(accumulator) → returns Optional<T> (because stream might be empty).
		 * Identity = starting value (0 for sum, 1 for product, empty string for concat).
		 * Common uses: sum, product, max, min, string concatenation.
		 */
		System.out.println();
		System.out.println("Reduce");
		
		// add using reduce
		int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum = " + sum); 
        
        // multiply using reduce
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product = " + product); 
        
        // Concat list
        String resultss = list1.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("Concatenated = " + resultss.trim());
        
        // Find Maximum
        int maxi = numbers.stream()
                .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

        System.out.println("Max = " + maxi);
        
        // Using Optional
        Optional<Integer> sums = numbers.stream()
                .reduce((a, b) -> a + b);

        sums.ifPresent(s -> System.out.println("Sum = " + s));
        
		/*
		 * Advanced Collections
		 * 
		 * 👉 joining() → Concatenate strings.
		 * 👉 toSet() / toCollection() → Collect into different collection types.
		 * 👉 summarizingDouble() → Get stats (count, sum, min, max, average).
		 * 👉 partitioningBy() → Split into two groups (true/false). 
		 * 👉 groupingBy() → Group into multiple buckets.
		 * 👉 mapping() → Transform elements inside grouping.
		 * 👉 reducing() → Custom reduction (like reduce but inside collect).
		 */
        
        System.out.println();
		System.out.println("Advanced Collections");
        
        // joining() → Concatenate Strings
        String join = list1.stream()
                .collect(Collectors.joining(", "));
        System.out.println(join); 
		
        // toSet() → Collect into a Set
        Set<Integer> uniqueNumbers = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println(uniqueNumbers); 
        
        // toCollection() → Collect into a specific collection
        Vector<String> vector = list1.stream()
                .collect(Collectors.toCollection(Vector::new));
        System.out.println(vector); 
        
        // summarizingDouble() → Statistical summary
        DoubleSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingDouble(Integer::intValue));
        System.out.println("Count = " + stats.getCount());   
        System.out.println("Sum = " + stats.getSum());       
        System.out.println("Min = " + stats.getMin());        
        System.out.println("Max = " + stats.getMax());       
        System.out.println("Average = " + stats.getAverage());
        
        // partitioningBy() → Split into two groups
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even = " + partitioned.get(true));  
        System.out.println("Odd = " + partitioned.get(false));
		
		// groupingBy() → Group into multiple categories
        Map<Character, List<String>> grouped = list1.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println(grouped);
        // Example :: {A=[Alice, Anna], B=[Bob], C=[Charlie], D=[David]}
        
        // mapping() → Transform while grouping
        Map<Character, List<Integer>> nameLengthsByLetter = list1.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0),
                        Collectors.mapping(String::length, Collectors.toList())));
        System.out.println(nameLengthsByLetter);
        // Example :: {A=[5, 4], B=[3], C=[7], D=[5]}
        
        // reducing() → Custom reduction inside collect
        int sumy = numbers.stream()
                .collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println(sumy); 
        
		/*
		 * It is a collector (from Collectors) that you pass to the collect() method.
		 * It allows you to do reductions inside collect(), especially useful when
		 * combining with groupingBy(), partitioningBy(), or other multi-level
		 * collectors.
		 * Think of it as "reduce in collector style".
		 */
        
        // When reducing is more powerful
        Map<Character, Optional<String>> longestNameByFirstChar = list1.stream()
                .collect(Collectors.groupingBy(
                    name -> name.charAt(0),   // group by first character
                    Collectors.reducing((s1, s2) -> s1.length() >= s2.length() ? s1 : s2)
                ));

        System.out.println(longestNameByFirstChar);
        
       // Grouping by -> {B=[Bob, Bill], J=[John, Jack, Jill]}
       // Group names by their first character
        Map<Character, List<String>> groupedd = names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println(groupedd);
        
		/*
		 * Parallel Stream
		 * 
		 * Normally, when you use a stream(), the elements are processed sequentially
		 * (one after another, single thread).
		 * 
		 * A parallel stream splits the data into multiple chunks and processes them in
		 * parallel (multi-threaded) using the ForkJoinPool (common thread pool in
		 * Java).
		 * 
		 * 👉 You get parallel processing without writing explicit multi-threading code.
		 */
        
        System.out.println();
		System.out.println("Parallel Stream");
        
        //BestExample
		List<Integer> parallelStream = IntStream.rangeClosed(1, 10_000_000).boxed().collect(Collectors.toList());

		// Sequential
		long startSeq = System.currentTimeMillis();
		long sumSeq = parallelStream.stream().mapToLong(n -> n * n) // square each number
				.sum();
		long endSeq = System.currentTimeMillis();
		System.out.println("Sequential Sum: " + sumSeq + " | Time: " + (endSeq - startSeq) + " ms");

		// Parallel
		long startPar = System.currentTimeMillis();
		long sumPar = parallelStream.parallelStream().mapToLong(n -> n * n) // square each number
				.sum();
		long endPar = System.currentTimeMillis();
		System.out.println("Parallel Sum: " + sumPar + " | Time: " + (endPar - startPar) + " ms");
		
		/*
		 * Fork Join Pool
		 * 
		 * What is ForkJoinPool?
		 * It is a special type of ExecutorService in Java.
		 * 
		 * Designed for "divide and conquer" algorithms → a big task is split into
		 * smaller subtasks, processed in parallel, and then results are combined.
		 * Used internally by:
		 * 		parallelStream() 
		 * 		CompletableFuture
		 * 		Recursive tasks (ForkJoinTask)
		 */
		
		// Infinite Streams
		System.out.println();
		System.out.println("Infinite Streams");
			
		Stream.generate(Math::random)
	      .limit(5) // important to avoid infinite loop
	      .forEach(System.out::println);
		
		Stream.iterate(2, i -> i * 2)
	      .limit(5)
	      .forEach(System.out::println);
		
		// File Operations with Streams -  Need to see briefly
		System.out.println();
		System.out.println("File Operations");
		String[] words = { "hello", "refer", "world", "level" };

		try (PrintWriter pw = new PrintWriter(
		        Files.newBufferedWriter(Paths.get("output.txt")))) {
		    Stream.of(words).forEach(pw::println);
		}
		
		//***************Java 9 Streams Improvements*********************//
		
		/*
		 * ✅ takeWhile 
		 * ✅ dropWhile 
		 * ✅ New iterate (3-argument version) 
		 * ✅ ofNullable 
		 * ✅ Stream.concat improvements
		 */
		
		/*
		 * Takes elements while condition is true.
		 * Stops immediately when condition fails (short-circuits).
		 */
		System.out.println();
		System.out.println("Java 9 Streams Improvements");
		
		//takeWhile
		Stream.iterate(1, i -> i + 1).takeWhile(n -> n <= 5).forEach(System.out::print);

		// dropWhile(Predicate)
		System.out.println();
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8).dropWhile(x -> x < 5).forEach(System.out::print);
		
		// infinite stream
		//java 8 
		//Stream.iterate(1, i -> i + 1); 
		//java 9
		System.out.println();
		Stream.iterate(1, i -> i <= 10, i -> i + 1)
	      .forEach(System.out::print);
		
		//ofNullable()
		/*
		 * Safely create a stream from a possibly null value. In Java 8, Stream.of(null)
		 * → throws NullPointerException.
		 */

		Stream<String> s1 = Stream.ofNullable("Hello");
		Stream<String> s2 = Stream.ofNullable(null);
		System.out.println();
		System.out.println(s1.count()); // 1
		System.out.println(s2.count()); // 0
		
		/*
		 * Java 8 concat() could only merge two streams.
		 * 
		 * In Java 9, you can nest it with Stream.of() to concatenate multiple streams:
		 */

		Stream<String> ss1 = Stream.of("A", "B");
		Stream<String> ss2 = Stream.of("C", "D");
		Stream<String> ss3 = Stream.of("E", "F");

		Stream<String> merged = Stream.of(ss1, ss2, ss3)
		                              .flatMap(s -> s);
		System.out.println();
		merged.forEach(System.out::print);

		// Special Cases for Primitive Streams – concat()
		System.out.println();
		System.out.println("Special Cases for Primitive Streams");
		
		IntStream first1 = IntStream.of(1, 2, 3);
		IntStream second = IntStream.of(4, 5, 6);

		IntStream.concat(first1, second).forEach(System.out::println);
		//LongStream.concat(first, second).forEach(System.out::println);
		//DoubleStream.concat(first, second).forEach(System.out::println);

	}
}
