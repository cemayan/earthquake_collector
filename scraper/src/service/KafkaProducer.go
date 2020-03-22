package service

import (
	"context"
	"encoding/json"
	"fmt"
	"strings"

	"github.com/cemayan/earthquake_collector/scraper/src/types"
	"github.com/cemayan/earthquake_collector/scraper/src/types/Earthquake"
	"github.com/segmentio/kafka-go"
)

func newKafkaWriter(kafkaURL string, topic string) *kafka.Writer {
	return kafka.NewWriter(kafka.WriterConfig{
		Brokers:  strings.Split(kafkaURL, ";"),
		Topic:    topic,
		Balancer: &kafka.LeastBytes{},
	})
}

// KafkaProducerHandler comment
func KafkaProducerHandler(configs types.Config, lastEq []byte) {

	kafkaWriter := newKafkaWriter(configs.BootstrapServer, configs.TopicId)
	defer kafkaWriter.Close()

	var eq Earthquake.Earthquake
	json.Unmarshal(lastEq, &eq)

	msg := kafka.Message{
		Key:   []byte(fmt.Sprintf("address-%s", eq.Name)),
		Value: lastEq,
	}

	err := kafkaWriter.WriteMessages(context.Background(), msg)
	fmt.Println(err)
}
