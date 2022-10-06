package assignment2021.codeprovided.handoutquestions;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import assignment2021.codeprovided.fitnesstracker.Participant;


/*
 * AbstractFitnessQuestions.java  	2.0  21/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * AbstractFitnessQuestions.java
 *
 * Abstract class designed to be extended. 
 * Provides basic abstract methods to answer the questions in the handout.
 *
 * @version 2.0  21/03/2022
 *
 * @author Islam Elgendy / Ben Clegg / Maria-Cruz Villa-Uriol
 */
public abstract class AbstractFitnessQuestions {

	private Collection<Participant> participants;
	
	/**
	 * constructor - loads the participants and their fitness tracker measurements,
	 * provided the path of the folder containing the sample data text files,
	 * and initialises the list of participantsList
     */
	public AbstractFitnessQuestions(Collection<Participant> participants) {
		this.participants = participants;
	}
	
	public Collection<Participant> getParticipants() {
		return participants;
	}
    
	// list of abstract methods starts
    /**
     * getTotalParticipants method - Answer to Q1
     * 1 - uses the List of participants, where each one is a Participant object
     * 2 - and then returns the number of total participants
     *
     * @return Total number of participants
     */
    public abstract int getTotalParticipants();
    
    /**
     * getParticipantsNumberWithHRM method - Answer to Q2
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total number of participants that have heart rate measurements
     *
     * @return Total number of participants that have heart rate measurements
     */
    public abstract int getParticipantsNumberWithHRM();
    
    /**
     * getParticipantsNumberWithStepsM method - Answer to Q3
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total number of participants that have steps measurements
     *
     * @return Total number of participants that have steps measurements
     */
    public abstract int getParticipantsNumberWithStepsM();
    
    /**
     * getParticipantsNumberWithDistanceM method - Answer to Q4
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total number of participants that have distance measurements
     *
     * @return Total number of participants that have distance measurements
     */
    public abstract int getParticipantsNumberWithDistanceM();

    /**
     * getParticipantsNumberWithEEM method - Answer to Q5
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total number of participants that have energy expenditure measurements
     *
     * @return Total number of participants that have energy expenditure measurements
     */
    public abstract int getParticipantsNumberWithEEM();

    /**
     * getTotalHRMCount method - Answer to Q6
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total count of heart rate measurements in the whole dataset.
     *
     * @return Value with the total count of heart rate measurements in the whole dataset.
     */
    public abstract int getTotalHRMCount();
    
    /**
     * getTotalStepsCount method - Answer to Q7
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total count of steps measurements in the whole dataset.
     *
     * @return Value with the total count of steps in the whole dataset.
     */
    public abstract int getTotalStepsCount();
    
    /**
     * getTotalDistanceCount method - Answer to Q8
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total count of distance measurements in the whole dataset.
     *
     * @return Value with the total count of distance measurements in the whole dataset.
     */
    public abstract int getTotalDistanceCount();
    
    /**
     * getTotalEECount method - Answer to Q9
     * 1 - using the List of participants, each one is a Participant object
     * 2 - and then returns the total count of energy expenditure measurements in the whole dataset.
     *
     * @return Value with the total count of energy expenditure measurements in the whole dataset.
     */
    public abstract int getTotalEECount();

    /**
     * getHRMCountPerFT method - Answer to Q10
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns a list of integers for the total count of heart rate measurements for each fitness tracker for the whole dataset.
     *
     * @return List with count of heart rate measurements for each fitness tracker for the whole dataset.
     */
    public abstract List<Integer> getHRMCountPerFT();
    
    /**
     * getEEMCountForFT1 method - Answer to Q11
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns a list of integers for the total count of energy expenditure measurements for fitness tracker FT1 for the whole dataset.
     *
     * @return Total count of energy expenditure measurements for fitness tracker FT1 for the whole dataset.
     */
    public abstract int getEEMCountForFT1();
    
    /**
     * getStepsMCountForFT234 method - Answer to Q12
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns a list of integers for the total count of steps measurements for fitness trackers FT2, FT3 and FT4 for the whole dataset.
     *
     * @return List with count of steps measurements for fitness trackers FT2, FT3 and FT4 for the whole dataset.
     */
    public abstract List<Integer> getStepsMCountForFT234();
    
    /**
     * getDistanceMCountForFT5 method - Answer to Q13
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns the total count of distance measurements for fitness tracker FT5 for the whole dataset.
     *
     * @return Total count of distance measurements for fitness tracker FT5 for the whole dataset.
     */
    public abstract int getDistanceMCountForFT5();
    
    /**
     * getHighestNumberOfStepsParticipants method - Answer to Q14
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with the highest number of steps
     *
     * @return Set of participant/s IDs with the highest number of steps.
     */
    public abstract Set<String> getHighestNumberOfStepsParticipants();
  
    /**
     * getHighestWalkedDistanceParticipants method - Answer to Q15
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with the highest walked distance
     *
     * @return Set of participant/s IDs with the highest walked distance.
     */
    public abstract Set<String> getHighestWalkedDistanceParticipants();
   
    /**
     * getGlobalAverageStepsForFT1 method - Answer to Q16
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns the global average number of steps for FT1 for the whole dataset.
     *
     * @return The global average number of steps for FT1 for the whole dataset.
     */
    public abstract double getGlobalAverageStepsForFT1();
    
    /**
     * getAvgStepsAboveGlobalParticipantsForFT1 method - Answer to Q17
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual 
     *     participant number of steps above the global average number of steps for FT1
     *
     * @return List of participant/s IDs with with an average individual participant 
     * participant number of steps above the global average number of steps for FT1.
     */
    public abstract List<String> getAvgStepsAboveGlobalParticipantsForFT1();
    
    /**
     * getAvgStepsBelowGlobalParticipantsForFT1 method - Answer to Q18
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual 
     *     participant number of steps below the global average number of steps for FT1
     *
     * @return List of participant/s IDs with with an average individual participant 
     * participant number of steps below the global average number of steps for FT1.
     */
    public abstract List<String> getAvgStepsBelowGlobalParticipantsForFT1();
    
    /**
     * getGlobalAverageEEForFT2FT3 method - Answer to Q19
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns the global average energy expenditure for FT2 and FT3, for the whole dataset.
     *
     * @return The global average energy expenditure for FT2 and FT3, for the whole dataset.
     */
    public abstract double getGlobalAverageEEForFT2FT3();
    
    /**
     * getAvgEEAboveGlobalParticipantsForFT2FT3 method - Answer to Q20
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual 
     *     participant number of steps above the global average energy expenditure for FT2 and FT3
     *
     * @return List of participant/s IDs with with an average individual participant 
     * energy expenditure above the global average energy expenditure for FT2 and FT3.
     */
    public abstract List<String> getAvgEEAboveGlobalParticipantsForFT2FT3();
    
    /**
     * getAvgEEBelowGlobalParticipantsForFT2FT3 method - Answer to Q21
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual 
     *     participant number of steps below the global average energy expenditure for FT2 and FT3
     *
     * @return List of participant/s IDs with with an average individual participant 
     * participant number of steps below the global average energy expenditure for FT2 and FT3.
     */
    public abstract List<String> getAvgEEBelowGlobalParticipantsForFT2FT3();
    
    /**
     * getGlobalAverageHR method - Answer to Q22
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns the global average heart rate for the whole dataset.
     *
     * @return The global average heart rate for the whole dataset.
     */
    public abstract double getGlobalAverageHR();
    
    /**
     * getAvgHRAboveGlobalParticipants method - Answer to Q23
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual participant 
     * heart rate above the global average heart rate 
     *
     * @return List of participant/s IDs with with an average individual participant 
     * heart rate above the global average heart rate.
     */
    public abstract List<String> getAvgHRAboveGlobalParticipants();
    
    /**
     * getAvgHRBelowGlobalParticipants method - Answer to Q24
     * 1 - using the List of fitness trackers, each one is a Tracker object
     * 2 - and then returns list of participant/s with an average individual participant 
     * heart rate below the global average heart rate 
     *
     * @return List of participant/s IDs with with an average individual participant 
     * heart rate below the global average heart rate.
     */
    public abstract List<String> getAvgHRBelowGlobalParticipants();
    

}
