package com.example.bunsanedthinking_springback.entity.termination;

import java.util.ArrayList;

public interface TerminationList {

/**
 * 
 * @param contract
 */
public void add(Termination contract);

/**
 * 
 * @param id
 */
public void delete(int id);

/**
 * 
 * @param id
 */
public Termination get(int id);

/**
 * 
 * @param id
 */
public void update(int id);

public ArrayList<Termination> getAllTerminatingContract();
public ArrayList<Termination> getAllUnprocessedTerminatingContract();
public ArrayList<Termination> getAllProcessedTerminatingContract();
public Termination getTerminatingContractById(int id);
}