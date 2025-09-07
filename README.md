# Java-Streams

## 📌 Java Streams & Related Concepts – Synopsis

### 🔹 Core Stream Concepts (Java 8)
- Stream basics – operations on collections in a functional style.  
- Stream vs Collection – stream is one-time-use, lazy, declarative.  
- Intermediate & Terminal operations.  
- flatMap, flatMapToInt, flatMapToLong, flatMapToDouble – flattening nested structures (lists of lists, arrays inside lists).  

### 🔹 Specialized Streams
- IntStream, LongStream, DoubleStream – primitive specializations for performance.  
- Creation: mapToInt, IntStream.of(), IntStream.range().  
- Specialized operations: sum(), average(), summaryStatistics().  

### 🔹 Reduction Operations
- reduce() – combines elements into one result using identity + accumulator.  
- reducing() – collector version of reduce(), mainly used with groupingBy() or partitioningBy().  
- Difference between reduce and reducing explained.  

### 🔹 Advanced Collectors
- Collectors.toList(), toSet(), toCollection()  
- joining() – concatenates strings with delimiter.  
- summarizingDouble() & summaryStatistics() – statistics like count, min, max, avg, sum.  
- partitioningBy() – divides into 2 groups (true/false).  
- groupingBy() – groups elements into multiple groups by classifier function.  
- mapping() – transforms values inside grouping.  
- reducing() – custom reduction inside collectors.  
