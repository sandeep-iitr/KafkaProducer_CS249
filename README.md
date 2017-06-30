# KafkaProducer_CS249

This code contains the simple kakfa producer which is used to simulate the health data from the real datasets for the complex event processing application project.

## Apache Kafka
Apache Kafka is a streaming platform which enables users to publish data and also subscribe to different streams of records. Kafka stores the streams in a fault tolerant way. It is used to build reliable real-time streaming data pipelines. Further, it runs as a cluster and implements the concept of topics or feed name which records are published to. In our current system abstraction, every user is publishing its data to a particular topic designated for the stream. Each topic internally maintains all the records for the configurable retention period. This capability can be used for health conditions that require using past data. 

## Implementation
The prototype system was implemented using Java. We simulated the multiple users by sending real-time data via different Java applications to the Kafka cluster. The implementation of both use cases is using Spark streaming in Java, and each is being run as a separate job on the local Spark cluster. The jobs do the real-time health analytics and produce the heart risk and stress index as output.

## Operation in Spark RDDs
In this section we will discuss how to define the use cases in the spark map reduce framework and the other transformations and operations supported by it. The goals behind these definitions is that, these operations are highly parallel and fault tolerable. Also in future these operations may be individually moved to different places for the reason of faster processing, reliability and security. 

Kafka producer is sending the data in the Json array format using the windows of sizes required for the heart risk measurement and stress index measurement. We created severals RDDs from the data received using the spark transformations. Logically each RDD is a different stages in the signal processing and are equivalent to the peaks in the ECG and BP signals, along with different operations on it.

![System Architecture](https://github.com/sandeep-iitr/ComplexEventDetection_CS249/blob/master/figs/system.jpg)
