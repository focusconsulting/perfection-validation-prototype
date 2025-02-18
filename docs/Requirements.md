# Requirements

This is the initial draft/concepts of requirements that a prototype will get evaluated against.  It's not meant to be comprehensive, but rather provide an initial set of guide posts as the prototype is iterated on.

## Functional

- System must generate "corrected" fields (i.e. Rounding up W2 dollar amounts)
- System must generate validation messages
- System must send a payload with original values, corrected values and validation messages
- Rules can be dependent on upstream rule execution

## Non-Functional

- Ideally, rules can be visualized such that business stakeholders can reason about them with developers
- Preference for using open source tooling
- Rules and processes can be easily deployed (i.e. without redeploying the whole system)
