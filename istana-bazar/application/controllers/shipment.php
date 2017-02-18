<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Shipment extends CI_Controller {

  //make private variable
  private $username = '';
  private $apiKey = '';

  public function index()
  {
    $this->load->helper('url');
    $this->load->view('shipment_new');
  }
  //check origin shipment
  function checkOrigin()
  {
    //get origin from input
    $origin = $this->input->post('origin');
    //set origin an data to send
    $urlOrigin = "http://localhost:8889/tracing/istanabazar/origin/key/{$origin}";
    //set data to send
    $postData = array(
      "username" => $this->username,
      "api_key" => $this->apiKey);
    try {
        //initialize to set properties
        $ch = curl_init($urlOrigin);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $postData);
        // execute! to get response
        $responseData = curl_exec($ch);
        // close the connection, release resources used
        curl_close($ch);
        // do anything you want with your response
        var_dump($responseData);

        return $responseData;
    } catch (Exception $ex) {
        echo $ex->getMessage();
        return "";
    }
    
  }
  //check destination shipment
  function checkDestination() 
  {
    //get destination from input
    $destination = $this->input->post('destination');
    //get url destination
    $urlOrigin = "http://localhost:8889/tracing/istanabazar/dest/key/{$destination}";
    //set data to send
    $postData = array(
      "username" => $this->username,
      "api_key" => $this->apiKey);
    try {
        //initialize to set properties
        $ch = curl_init($urlOrigin);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $postData);
        // execute! to get response
        $responseData = curl_exec($ch);
        // close the connection, release resources used
        curl_close($ch);
        // do anything you want with your response
        var_dump($responseData);

        return $responseData;
    } catch (Exception $ex) {
        echo $ex->getMessage();
        return "";
    }
  }
  
  function checkFareResult() 
  {
     //get origin from input
    $origin = $this->input->post('origin');
    //get destination from input
    $destination = $this->input->post('destination');
     //get destination from input
    $weight = $this->input->post('weight');
    //get url to check fare result
    $urlOrigin = "http://localhost:8889/tracing/istanabazar/price/";
    //set data to send
    $postData = array(
      "username" => $this->username,
      "api_key" => $this->apiKey,
      "from" => $origin,
      "thru" => $destination,
      "weight" => $weight);
    try {
        //initialize to set properties
        $ch = curl_init($urlOrigin);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $postData);
        // execute! to get response
        $responseData = curl_exec($ch);
        // close the connection, release resources used
        curl_close($ch);
        // do anything you want with your response
        var_dump($responseData);

        return $responseData;
    } catch (Exception $ex) {
        echo $ex->getMessage();
        return "";
    }
  }
}
