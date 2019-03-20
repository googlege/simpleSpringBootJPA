package de.homedev.rest.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

@Api(tags = "Measurement")
@RestController
public class MesureRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MesureRestController.class);
    public static final String JWT_KEY_AUTH = "jwt";

//    @Autowired
//    private ObjectMapper objectMapper;

    @ApiOperation(value = "Receives a measure from the trolley.", authorizations = @Authorization(JWT_KEY_AUTH))
    @ApiImplicitParams(@ApiImplicitParam(value = "RawMeasure data as Json. It is a list of measures", name = "measures", dataType = "RawWagenMeasure", required = true, type = "string", allowMultiple = true))
    @ApiResponses({
            @ApiResponse(code = 204, message = "RawMeasure received and no data will be returned. Even if there is an error this response will be served!") })
    @PostMapping(value = { "${app.restPath}" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveCartInfo(@RequestBody(required = false) final String measures) {

        LOGGER.info("Recived:" + measures);
//        final UUID parentUuid = UUID.randomUUID();
//        final Iterator<JsonNode> nodes = getNodesIterator(parentUuid, measures);
//        while (nodes.hasNext()) {
//            JsonNode node = nodes.next();
//            LOGGER.info("Recived node:" + node.toString());
//        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hello")
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }

//    private Iterator<JsonNode> getNodesIterator(final UUID uuid, final String jsonAsString) {
//        final JsonNode rootNode;
//        try {
//            rootNode = objectMapper.readTree(jsonAsString);
//        } catch (final IOException e) {
//            LOGGER.error("Could not read json message with parent UUID '{}': {}", uuid, jsonAsString, e);
//            return Collections.emptyIterator();
//        }
//
//        if (rootNode.isArray()) {
//            return rootNode.iterator();
//        } else {
//            return Collections.singletonList(rootNode).iterator();
//        }
//    }
}