# Java-Streams

📌 Java Streams & Related Concepts – Synopsis

🔹 Core Stream Concepts (Java 8)
      Stream basics – operations on collections in a functional style.
      Stream vs Collection – stream is one-time-use, lazy, declarative.
      Intermediate & Terminal operations.
      flatMap, flatMapToInt, flatMapToLong, flatMapToDouble – flattening nested structures (lists of lists, arrays inside lists).

🔹 Specialized Streams
      IntStream, LongStream, DoubleStream – primitive specializations for performance.
      Creation: mapToInt, IntStream.of(), IntStream.range().
      Specialized operations: sum(), average(), summaryStatistics().

🔹 Reduction Operations
      reduce() – combines elements into one result using identity + accumulator.
      reducing() – collector version of reduce(), mainly used with groupingBy() or partitioningBy().
      Difference between reduce and reducing explained.

🔹 Advanced Collectors
      Collectors.toList(), toSet(), toCollection()
      joining() – concatenates strings with delimiter.
      summarizingDouble() & summaryStatistics() – statistics like count, min, max, avg, sum.
      partitioningBy() – divides into 2 groups (true/false).
      groupingBy() – groups elements into multiple groups by classifier function.
      mapping() – transforms values inside grouping.
      reducing() – custom reduction inside collectors.

🔹 Parallel Streams
      Using parallelStream() or .parallel() for concurrent execution.
      Backed by ForkJoinPool (common pool by default).
      Pros: performance boost for large data.
      Cons: thread-safety issues, non-deterministic order, not always faster.
      Explained ForkJoinPool as the underlying parallel task executor.

🔹 Infinite Streams
      Stream.generate() – creates infinite stream from a Supplier.
      Stream.iterate() – creates infinite sequence from seed + function.
      Need to use .limit() to terminate.

🔹 Streams with File Operations
      Writing to file using PrintWriter + Stream.forEach().
      Reading from file using Files.lines(Path) → returns Stream<String>.
      Example: filtering palindromes (refer, level).

🔹 Java 9 Stream Enhancements
      takeWhile() – takes elements until predicate fails.
      dropWhile() – skips elements while predicate true, then collects rest.
      iterate(seed, condition, function) – improved iterate with condition.
      ofNullable() – converts null-safe object to stream (0 or 1 element).
      concat() – joins two streams (works for Stream, IntStream, LongStream, DoubleStream).

🔹 Special Cases for Primitive Streams
      IntStream.concat(), LongStream.concat(), DoubleStream.concat() examples.

📌 Beyond Streams
🔹 CompletableFuture (Java 8)
      Asynchronous programming, non-blocking tasks.
      Methods: supplyAsync(), thenApply(), thenCompose(), join(), get().

🔹 Optional (Java 8)
      Null-safe container for values.
      Methods: of(), empty(), isPresent(), orElse(), ifPresent(), map().

🔹 Generics (Java 5+)
      Type-safe reusable code.
      Works with classes, methods, and collections.
      Removes need for casting.
      Supports wildcards (? extends T, ? super T).
