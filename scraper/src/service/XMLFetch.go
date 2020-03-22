package service

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

// Fetch comment
func Fetch(url string) []byte {

	resp, err := http.Get(url)

	if err != nil {
		fmt.Println("There was an error downloading the file!")
	}
	defer resp.Body.Close()

	data, err := ioutil.ReadAll(resp.Body)

	if err != nil {
		fmt.Errorf("Read body: %v", err)
	}
	return data
}
