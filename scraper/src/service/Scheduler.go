package service

import (
	"bytes"
	"encoding/json"
	"fmt"
	"github.com/allegro/bigcache"
	"github.com/cemayan/earthquake_collector/scraper/src/types"
)

// ScheduleJob comment
func ScheduleJob(configs types.Config, cacheManager *bigcache.BigCache) {
	fmt.Println("Parsing XML file...", "http://udim.koeri.boun.edu.tr/zeqmap/xmlt/son24saat.xml")
	data := Fetch("http://udim.koeri.boun.edu.tr/zeqmap/xmlt/son24saat.xml")
	fmt.Println("Operation completed!")
	parsedData := Parse(data)

	eq, _ := json.Marshal(parsedData[len(parsedData)-1])
	lastEarthQuake, _ := cacheManager.Get("last-earthquake")

	res := bytes.Compare(eq, lastEarthQuake)

	if res != 0 {
		cacheManager.Set("last-earthquake", eq)
		KafkaProducerHandler(configs, eq)
	} else {
		fmt.Println("there is no current earthquake right now")
	}

}
