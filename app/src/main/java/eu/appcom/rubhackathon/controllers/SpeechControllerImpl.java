package eu.appcom.rubhackathon.controllers;

import android.speech.RecognitionListener;
import javax.inject.Inject;

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
public class SpeechControllerImpl implements SpeechController {

  @Inject public SpeechControllerImpl() {
  }

  @Override public boolean isRecognitionAvailable() {
    return false;
  }

  @Override public void startSpeechRecognizer() {

  }

  @Override public void stopSpeechRecognizer() {

  }
}
