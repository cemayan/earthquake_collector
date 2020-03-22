package util

import (
	"encoding/json"
	"github.com/cemayan/earthquake_collector/scraper/src/types"
	"log"
	"os"
)

func GetConfigs() types.Config {
	configfile, _ := os.Open("./config/config.json")
	defer configfile.Close()
	decoder := json.NewDecoder(configfile)
	configuration := types.Config{}
	err := decoder.Decode(&configuration)

	if err != nil {
		log.Fatalln("Config file could not be read " + err.Error())
	}

	return configuration
}
