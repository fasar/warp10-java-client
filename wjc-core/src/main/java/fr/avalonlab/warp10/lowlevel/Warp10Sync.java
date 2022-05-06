package fr.avalonlab.warp10.lowlevel;

import fr.avalonlab.warp10.exceptions.W10ServerException;
import fr.avalonlab.warp10.model.WSelector;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;

/**
 * Low level Warp10 client to dialog with a Warp10 Server
 */
public interface Warp10Sync {

    /**
     * Ingress a multiple line of data.
     *
     * @param gtsInputs List of GTS Inputs
     * @throws W10ServerException
     */
    void ingress(List<String> gtsInputs) throws W10ServerException;

    /**
     * Ingress a single line of data
     *
     * @param gtsInput GTS Input
     * @throws W10ServerException
     */
    void ingress(String gtsInput) throws W10ServerException;

    /**
     * Execute the Warpscript script in parameter.
     * Gives the result as a String
     *
     * @param warpScript script
     * @throws W10ServerException
     */
    String exec(String warpScript) throws W10ServerException;

    /**
     * Delete the data of the selector during the period from start to end.
     * <p>
     * https://warp10.io/content/03_Documentation/03_Interacting_with_Warp_10/07_Deleting_data
     *
     * @param selector Selector of the timeseries
     * @param start    Instant to start the delete
     * @param end
     * @throws W10ServerException
     */
    void delete(WSelector selector, Instant start, Instant end) throws W10ServerException;

    /**
     * Delete all the data of the selector
     * <p>
     * https://warp10.io/content/03_Documentation/03_Interacting_with_Warp_10/07_Deleting_data
     *
     * @param selector Selector of the timeseries
     * @throws W10ServerException
     */
    void deleteAll(WSelector selector) throws W10ServerException;

    /**
     * https://www.warp10.io/content/03_Documentation/03_Interacting_with_Warp_10/04_Fetching_data/01_Fetching_data
     *
     * <p>
     * for start and end:<br>
     *      <ul>
     *          <li>now and timespan: instead of the basic interval search, with the start and end timestamps in ISO8601 format, you can use two alternative formats for the interval parameters:</li>
     *          <li>If you want to fetch readings whose timestamps lie in a specific timespan before a timestamp (for example all the readings in the last minute) you can use the end timestamp (in time units since the Unix epoch) as now parameter and the timespan (in platform time units) as timespan.</li>
     *          <li>If you want to recover the last n readings before a given instant, you can use the instant timestamp (in microseconds since the Unix epoch) as now parameter and -n as timespan.</li>
     *      </ul>
     *
     * </p>
     *
     * @param selector Selector of the timeseries
     * @param dedup    if this parameter is true, sequences of successive datapoints with the same locations and value are compressed in the response, giving only the first and the last datapoints in the sequence.
     * @param start    the start and end timestamps defining the interval of the GTS to fetch. They are both in ISO8601 format, i.e. YYYY-MM-DDTHH:MM:SS.SSSSSSZ.
     * @param end      the start and end timestamps defining the interval of the GTS to fetch. They are both in ISO8601 format, i.e. YYYY-MM-DDTHH:MM:SS.SSSSSSZ.
     * @return
     */
    Iterator<String> fetchData(WSelector selector, boolean dedup, FETCH_FORMAT format, Instant start, Instant end) throws W10ServerException;

}
