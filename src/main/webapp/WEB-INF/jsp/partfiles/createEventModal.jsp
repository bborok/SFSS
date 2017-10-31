<%--Create Event Modal--%>
<div id="createEventModal" class="modal fade">
    <div class="modal-dialog">
        <%--Modal Content--%>

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x
                </button>
                <h3 id="myModalLabel1">Assign a shift</h3>
            </div>

            <%--Modal Body--%>
            <div class="modal-body">
                <form id="createAppointmentForm" class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label">Shift: </label>
                        <div class="controls">
                            <select name="eventCampus" id="eventCampus">
                                <option value='all' id='allCampuses' disabled="true" selected>Select
                                    a campus.
                                </option>
                                <option value="BURNABY" class="BURNABY">BURNABY</option>
                                <option value="SURREY" class="SURREY">SURREY</option>
                                <option value="VANCOUVER" class="VANCOUVER">VANCOUVER</option>
                            </select>
                            <select name="eventTitle" id="eventTitle">
                                <option value="SURREY" disabled="true" selected="selected">Select a
                                    SURREY Shift
                                </option>
                                <option value="SURREY" class="Community Presence">Community
                                    Presence
                                </option>
                                <option value="SURREY" class="Theft Prevention">Theft Prevention
                                </option>
                                <option value="SURREY" class="Special Events">Special Events
                                </option>
                                <option value="SURREY" class="Pedestrian Safety">Pedestrian Safety
                                </option>

                                <option value="VANCOUVER" disabled="true" selected="selected">Select
                                    a VANCOUVER Shift
                                </option>
                                <option value="VANCOUVER" class="Community Presence">Community
                                    Presence
                                </option>
                                <option value="VANCOUVER" class="Theft Prevention">Theft
                                    Prevention
                                </option>
                                <option value="VANCOUVER" class="Special Events">Special Events
                                </option>
                                <option value="VANCOUVER" class="Pedestrian Safety">Pedestrian
                                    Safety
                                </option>

                                <option value="BURNABY" disabled="true" selected="selected">Select a
                                    BURNABY Shift
                                </option>
                                <option value="BURNABY" class="Information and Lost & Found Kiosk">
                                    Information and Lost & Found Kiosk
                                </option>
                                <option value="BURNABY" class="Speed Watch / Moving Traffic">Speed
                                    Watch / Moving Traffic
                                </option>
                                <option value="BURNABY" class="Community Presence">Community
                                    Presence
                                </option>
                                <option value="BURNABY" class="Safety Screen">Safety Screen</option>
                                <option value="BURNABY" class="Theft Prevention">Theft Prevention
                                </option>
                                <option value="BURNABY" class="Auto Theft Prevention">Auto Theft
                                    Prevention
                                </option>
                                <option value="BURNABY" class="Bike Presence">Bike Presence</option>
                                <option value="BURNABY" class="Special Events">Special Events
                                </option>
                                <option value="BURNABY" class="Smoking Checks">Smoking Checks
                                </option>
                                <option value="BURNABY" class="Pedestrian Safety">Pedestrian
                                    Safety
                                </option>

                            </select>
                            <input type="hidden" id="apptStartTime"/>
                            <input type="hidden" id="apptEndTime"/>
                            <input type="hidden" id="apptAllDay"/>
                        </div>
                        <label class="control-label">When:</label>
                        <label id="when"></label>
                    </div>
                    <div class="control-group">
                    </div>
                    <label class="control-label">Volunteer:</label>
                    <%--Ajax request--%>
                    <h3>
                        <input type="text" name="eventMember" id="eventMember"
                               placeholder="Enter a volunteer.">
                    </h3>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                <%--AJAX Request--%>
                <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
            </div>
        </div>
    </div>
</div>