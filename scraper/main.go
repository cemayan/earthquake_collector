package main

import (
	"github.com/cemayan/earthquake_collector/scraper/src/service"
	"github.com/cemayan/earthquake_collector/scraper/src/types"
	"github.com/cemayan/earthquake_collector/scraper/src/util"
	"github.com/jasonlvhit/gocron"
)

var configs = types.Config{}

func main() {

	configs = util.GetConfigs()
	cacheManager := service.InitCache()


	scheduler := gocron.NewScheduler()

	if configs.ScheduleUnit == "SECOND" {
		scheduler.Every(configs.ScheduleInterval).Second().Do(service.ScheduleJob, configs, cacheManager)
	}else if configs.ScheduleUnit == "MINUTE" {
		scheduler.Every(configs.ScheduleInterval).Minute().Do(service.ScheduleJob, configs, cacheManager)
	}

	<-scheduler.Start()
}
